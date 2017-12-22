package com.yarmarq;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
        synopsisHeading = "\nUsage: ",
        descriptionHeading = "\nDescription:\n\t",
        parameterListHeading = "\nParameters:\n",
        optionListHeading = "\nOptions:\n",
        commandListHeading = "\nCommands:\n"
)
public abstract class AbstractCommand {
    @Option(
            names = {"-h", "-?", "--help"},
            usageHelp = true,
            description = "Print this help message."
    )
    protected boolean helpRequested;
}
