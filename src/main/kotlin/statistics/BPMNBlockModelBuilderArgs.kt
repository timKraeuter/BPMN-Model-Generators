package statistics

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.*

class StatPrinterArgs : CliktCommand(help = "This is a command-line tool for printing statistics about BPMN models.") {
    val outputPath by option("-p", "-path").help("Path for where the BPMN models are located (required)").required()
    override fun run() = Unit
}