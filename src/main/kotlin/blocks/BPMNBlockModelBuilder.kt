package blocks

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import org.camunda.bpm.model.bpmn.Bpmn
import org.camunda.bpm.model.bpmn.BpmnModelInstance
import org.camunda.bpm.model.bpmn.builder.AbstractFlowNodeBuilder
import org.camunda.bpm.model.bpmn.instance.FlowNode
import java.io.File
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicLong

class BPMNBlockModelBuilder {
    private var flowNodeBuilder: AbstractFlowNodeBuilder<*, *>
    private val idSequencer: AtomicLong = AtomicLong(0)

    init {
        flowNodeBuilder = Bpmn.createProcess().startEvent(nextId)
    }

    /** Block 1 adds three tasks to the process.  */
    fun block1(): BPMNBlockModelBuilder {
        val task1 = nextId
        val task2 = nextId
        val task3 = nextId
        flowNodeBuilder = flowNodeBuilder.serviceTask(task1).serviceTask(task2).serviceTask(task3)
        return this
    }

    /** Block 2 adds two exclusive gateways and two tasks as a block to the process.  */
    fun block2(): BPMNBlockModelBuilder {
        val gateway1 = nextId
        val task1 = nextId
        val task2 = nextId
        val gateway2 = nextId

        flowNodeBuilder =
            flowNodeBuilder
                .exclusiveGateway(gateway1)
                .serviceTask(task1)
                .moveToNode(gateway1)
                .serviceTask(task2)
                .moveToNode(task1)
                .exclusiveGateway(gateway2)
                .moveToNode(task2)
                .connectTo(gateway2)
        return this
    }

    private val nextId: String
        get() = createID(idSequencer.incrementAndGet())

    private val currentId: String
        get() = createID(idSequencer.get())

    /** Block 3 adds two parallel gateways and two tasks as a block to the process.  */
    fun block3(): BPMNBlockModelBuilder {
        val gateway1 = nextId
        val task1 = nextId
        val task2 = nextId
        val gateway2 = nextId

        flowNodeBuilder =
            flowNodeBuilder
                .parallelGateway(gateway1)
                .serviceTask(task1)
                .moveToNode(gateway1)
                .serviceTask(task2)
                .moveToNode(task1)
                .parallelGateway(gateway2)
                .moveToNode(task2)
                .connectTo(gateway2)
        return this
    }

    fun build(): BpmnModelInstance {
        return flowNodeBuilder.done()
    }

    companion object {
        private const val ID_FORMAT: String = "FlowNode_%s"
        suspend fun createModelsWithUpToXBlocks(path: String, numberOfBlocks: Int) {
            coroutineScope {
                val bpmnModelBuilder = BPMNBlockModelBuilder()
                var blocks = 0
                while (blocks < numberOfBlocks) {
                    bpmnModelBuilder.block1()
                    blocks++
                    addEndEventAndSaveInstance(path, bpmnModelBuilder, blocks)
                    if (blocks >= numberOfBlocks) {
                        break
                    }
                    bpmnModelBuilder.block2()
                    blocks++
                    addEndEventAndSaveInstance(path, bpmnModelBuilder, blocks)
                    if (blocks >= numberOfBlocks) {
                        break
                    }
                    bpmnModelBuilder.block3()
                    blocks++
                    addEndEventAndSaveInstance(path, bpmnModelBuilder, blocks)
                }
            }
        }

        private fun CoroutineScope.addEndEventAndSaveInstance(
            path: String,
            bpmnModelBuilder: BPMNBlockModelBuilder,
            blocks: Int,
        ) {
            val clone: BpmnModelInstance = bpmnModelBuilder.build().clone()
            val lastStringId = bpmnModelBuilder.currentId
            val lastId: Long = bpmnModelBuilder.idSequencer.get()
            launch {
                val lastElement: FlowNode = clone.getModelElementById(lastStringId)
                val instanceWithEndEvent: BpmnModelInstance =
                    lastElement.builder().endEvent(createID(lastId + 1)).done()
                val file = File(String.format("$path/%03d.bpmn", blocks))
                Bpmn.writeModelToFile(file, instanceWithEndEvent)
            }
        }

        private fun createID(id: Long): String {
            return String.format(ID_FORMAT, id)
        }
    }
}
