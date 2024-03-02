package statistics

import org.camunda.bpm.model.bpmn.Bpmn
import org.camunda.bpm.model.bpmn.instance.FlowElement
import org.camunda.bpm.model.bpmn.instance.FlowNode
import org.camunda.bpm.model.bpmn.instance.Gateway
import org.camunda.bpm.model.bpmn.instance.SequenceFlow
import java.nio.file.Files
import java.nio.file.Path

/** Print statistics for the BPMN files in the given folder.  */
fun printStats(folder: Path): Int {
    var numberOfModels = 0
    println("modelName;gateways;flowNodes;sequenceFlows;flowElements")
    Files.walk(folder).use { files ->
        files.forEach { bpmnFile: Path ->
            val fileName = bpmnFile.fileName.toString()
            if (fileName.endsWith(".bpmn")) {
                val bpmnModelInstance = Bpmn.readModelFromFile(bpmnFile.toFile())
                val numberOfFlowNodes =
                    bpmnModelInstance.getModelElementsByType(FlowNode::class.java).size
                val numberOfSequenceFlows =
                    bpmnModelInstance.getModelElementsByType(SequenceFlow::class.java).size
                val numberOfFlowElements =
                    bpmnModelInstance.getModelElementsByType(FlowElement::class.java).size
                val numberOfGateways = bpmnModelInstance.getModelElementsByType(
                    Gateway::class.java
                ).size

                // modelName;numberOfGateways;numberOfFlowNodes;numberOfSequenceFlows;numberOfFlowElements
                System.out.printf(
                    "%s;%s;%s;%s;%s%n",
                    fileName,
                    numberOfGateways,
                    numberOfFlowNodes,
                    numberOfSequenceFlows,
                    numberOfFlowElements
                )
                numberOfModels++
            }
        }
    }
    return numberOfModels
}
