package blocks

import formatDuration
import kotlin.system.measureTimeMillis

suspend fun main(args: Array<String>) {
    val parsedArgs = BPMNBlockModelGeneratorArgs()
    parsedArgs.main(args)

    val generationTime = measureTimeMillis {
        BPMNBlockModelBuilder.createModelsWithUpToXBlocks(
            parsedArgs.outputPath,
            parsedArgs.numberOfBlocks
        )
    }
    println(
        "Generated ${parsedArgs.numberOfBlocks} BPMN models at ${parsedArgs.outputPath} in ${
            formatDuration(
                generationTime
            )
        }."
    )
}