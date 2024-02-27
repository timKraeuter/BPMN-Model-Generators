import kotlinx.cli.*

class CLIArgs(parser: ArgParser) {
    val parallel_branches by parser.option(ArgType.Int, shortName = "b", description = "Number of parallel branches").default(3)
    val branch_length by parser.option(ArgType.Int, shortName = "l", description = "Length of parallel branches").default(3)
    val output_path by parser.option(ArgType.String, shortName = "p", description = "Path for the generated BPMN models").required()
}