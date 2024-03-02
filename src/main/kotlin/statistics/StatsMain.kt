package statistics

import formatDuration
import kotlin.io.path.Path
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val parsedArgs = StatPrinterArgs()
    parsedArgs.main(args)

    var numberOfModels : Int
    val generationTime = measureTimeMillis {
        numberOfModels = printStats(Path(parsedArgs.outputPath))
    }
    println(
        "Analyzed $numberOfModels BPMN models at ${parsedArgs.outputPath} in ${
            formatDuration(
                generationTime
            )
        }."
    )
}