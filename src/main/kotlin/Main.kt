import kotlinx.cli.ArgParser

fun main(args: Array<String>) {
    val parser = ArgParser("bpmn-model-generator")
    val parsedArgs = CLIArgs(parser)
    parser.parse(args)


    println("Length: ${parsedArgs.branch_length}")
    println("Branches: ${parsedArgs.parallel_branches}")
    println("Path: ${parsedArgs.output_path}")
}