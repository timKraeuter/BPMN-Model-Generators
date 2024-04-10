This repository contains the source code for three CLI tools:
1. **BPMNBlockModelGenerator**: Generate a BPMN model with a varying number of predefined blocks (see details below).
2. **BPMNParallelBranchModelGenerator** Generate a BPMN model with x parallel branches of length y (see details below).
3. **BPMNStatPrinter** Print statistics for all BPMN models in a given folder (see details below).

All CLI tools are prebuilt and contained in ./prebuilt-tools. You can check their arguments using the help command:

```bash
java -jar ./prebuilt-tools/BPMNParallelBranchModelGenerator.jar --help
```

The tools are prebuilt and tested with Java 20 (OpenJDK 20.0.2.1 2023-08-22).

# BPMNBlockModelGenerator
The CLI tool generates BPMN models from one up to a given **number of blocks**.
There are three predefined blocks:

![Blocks](./documentation/blocks.svg)

After the first three blocks the tool will repeat the blocks in the same order until it reaches the given number of blocks.
The following is an example of a BPMN model with three blocks:

![BPMN model with three blocks](./documentation/three-blocks.svg)

### Arguments (generated by `--help`):
```
Usage: bpmnblock-model-generator-args [<options>]

  This is a command-line tool for generating BPMN models with an increasing number of three predefined blocks.

Options:
  -b, -number-of-blocks=<int>  Number of blocks (default=3)
  -p, -path=<text>             Path for the generated BPMN models (required)
  -h, --help                   Show this message and exit
```

# BPMNParallelBranchModelGenerator

The CLI tool generates BPMN models using the following schema:

![Schema describing the generation](./documentation/parallel.svg)

### Arguments (generated by `--help`)
```cli
Usage: bpmnparallel-model-generator-args [<options>]

  This is a command-line tool for generating BPMN models with parallel branches up to the defined number and the defined length.

Options:
  -b, -number-of-branches=<int>  Number of parallel branches (default=3)
  -l, -length-of-branches=<int>  Length of parallel branches (default=3)
  -p, -path=<text>               Path for the generated BPMN models (required)
  -h, --help                     Show this message and exit
```

# BPMNStatPrinter
The CLI tool prints the file name, number of gateways, number of flow nodes, number of sequence flows, and number of flow elements to the console in CSV format.

### Arguments (generated by `--help`)
```
Usage: stat-printer-args [<options>]

  This is a command-line tool for printing statistics about BPMN models.

Options:
  -p, -path=<text>  Path where the BPMN models are located (required)
  -h, --help        Show this message and exit
```
