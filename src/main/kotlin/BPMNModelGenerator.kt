import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.*
import com.github.ajalt.clikt.parameters.types.int

class BPMNModelGenerator : CliktCommand() {
    val parallelBranches by option("-b", "-branch-number").help("Number of parallel branches").int().default(3)
    val branchLength by option("-l", "-branch-length").help("Length of parallel branches").int().default(3)
    val outputPath by option("-p", "-path").help("Path for the to be generated BPMN models").required()

    override fun run() = Unit
}