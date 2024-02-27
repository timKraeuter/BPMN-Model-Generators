import kotlinx.cli.ArgParser
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

suspend fun main(args: Array<String>) {
    val parser = ArgParser("bpmn-model-generator")
    val parsedArgs = CLIArgs(parser)
    parser.parse(args)

    // Run generation in parallel using coroutines
    val generationTime = measureTimeMillis {
        // Run generation in parallel using coroutines
        coroutineScope {
            for (i in 1..parsedArgs.parallel_branches) {
                for (j in 1..parsedArgs.branch_length) {
                    launch {
                        doGeneration(i, j, parsedArgs.output_path)
                    }
                }
            }
        }
    }
    println(
        "Generation to ${parsedArgs.output_path} finished in ${
            formatDuration(
                generationTime
            )
        }."
    )
}

fun formatDuration(durationMillis: Long): String {
    return if (durationMillis < 1000) {
        "$durationMillis ms"
    } else {
        "${durationMillis / 1000.0} s"
    }
}

fun doGeneration(branches: Int, branchLength: Int, outputPath: String) {
    ParallelBranchModelGenerator().generateParallelBranchModel(branches, branchLength, outputPath)

}