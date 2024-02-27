import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.*
import com.github.ajalt.clikt.parameters.types.int

class BPMNModelGenerator : CliktCommand(help = "This is a command-line tool for generating BPMN models with parallel branches up to the defined number and the defined length.") {
    val parallelBranches by option("-b", "-number-of-branches").help("Number of parallel branches").int().default(3)
    val branchLength by option("-l", "-length-of-branches").help("Length of parallel branches").int().default(3)
    val outputPath by option("-p", "-path").help("Path for the to be generated BPMN models").required()

    override fun run() = Unit
}