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

    private final String banner = " __      __                                                                 \n" +
            "|  \\    /  \\  Simple access to exchange rates and gold prices from NBP.pl.  \n" +
            " \\$$\\  /  $$  ______    ______   ______ ____    ______    ______    ______  \n" +
            "  \\$$\\/  $$  |      \\  /      \\ |      \\    \\  |      \\  /      \\  /      \\ \n" +
            "   \\$$  $$    \\$$$$$$\\|  $$$$$$\\| $$$$$$\\$$$$\\  \\$$$$$$\\|  $$$$$$\\|  $$$$$$\\\n" +
            "    \\$$$$    /      $$| $$   \\$$| $$ | $$ | $$ /      $$| $$   \\$$| $$  | $$\n" +
            "    | $$    |  $$$$$$$| $$      | $$ | $$ | $$|  $$$$$$$| $$      | $$__| $$\n" +
            "    | $$     \\$$    $$| $$      | $$ | $$ | $$ \\$$    $$| $$       \\$$    $$\n" +
            "     \\$$      \\$$$$$$$ \\$$       \\$$  \\$$  \\$$  \\$$$$$$$ \\$$        \\$$$$$$$\n" +
            "                                                                        | $$\n" +
            "                                       Data via: Narodowy Bank Polski.  | $$\n" +
            "                                         Author: Kasper Kądzielawa       \\$$\n";

    public void printBanner() {
        System.out.println(banner);
    }

    public void printPreDownloadMessage() {
        System.out.print("Downloading data...");
    }

    public void printPostDownloadMessage() {
        System.out.println(" Download finished!\n");
    }

    public void printGeneralErrorMessage() {
        System.out.println("An error occurred!");
    }
}
