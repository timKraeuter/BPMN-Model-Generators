package blocks

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.*
import com.github.ajalt.clikt.parameters.types.int

class BPMNBlockModelGeneratorArgs : CliktCommand(help = "This is a command-line tool for generating BPMN models with an increasing number of three predefined blocks.") {
    val numberOfBlocks by option("-b", "-number-of-blocks").help("Number of blocks (default=3)").int().default(3)
    val outputPath by option("-p", "-path").help("Path for the generated BPMN models (required)").required()

    override fun run() = Unit
}