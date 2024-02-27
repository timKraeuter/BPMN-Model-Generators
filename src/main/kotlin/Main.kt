import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

suspend fun main(args: Array<String>) {
    val parsedArgs = BPMNModelGenerator()
    parsedArgs.main(args)

    // Run generation in parallel using coroutines
    val generationTime = measureTimeMillis {
        // Run generation in parallel using coroutines
        coroutineScope {
            for (i in 1..parsedArgs.parallelBranches) {
                for (j in 1..parsedArgs.branchLength) {
                    launch {
                        doGeneration(i, j, parsedArgs.outputPath)
                    }
                }
            }
        }
    }
    println(
        "Generated ${parsedArgs.parallelBranches * parsedArgs.branchLength} BPMN models at ${parsedArgs.outputPath} in ${
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