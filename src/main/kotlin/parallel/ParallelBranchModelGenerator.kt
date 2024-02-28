package parallel

import org.camunda.bpm.model.bpmn.Bpmn
import org.camunda.bpm.model.bpmn.builder.ParallelGatewayBuilder
import java.io.File

internal class ParallelBranchModelGenerator {
    private var idSequencer = 1
    private fun nextID(): String {
        val id = idSequencer
        idSequencer++
        return "FlowNode_$id"
    }

    fun generateParallelBranchModel(parallelBranches: Int, branchLength: Int, path: String) {
        val pg1 =
            Bpmn.createProcess().startEvent(nextID()).parallelGateway(nextID())

        var pg2: ParallelGatewayBuilder? = null
        for (i in 0 until parallelBranches) {
            val id = nextID()
            var builder = pg1.serviceTask(id).name(id)
            for (j in 1 until branchLength) {
                val taskId = nextID()
                builder = builder.serviceTask(taskId).name(taskId)
            }
            if (i == 0) {
                pg2 = builder.parallelGateway(nextID())
                pg2.endEvent(nextID())
            } else {
                builder.connectTo(pg2!!.element.id)
            }
        }

        val model = pg1.done()
        val file =
            File(
                String.format(
                    "$path/p%02dx%02d.bpmn", parallelBranches, branchLength
                )
            )
        Bpmn.writeModelToFile(file, model)
    }
}
