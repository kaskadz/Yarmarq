package com.yarmarq.subcommand;

import picocli.CommandLine.*;

@Command(name = "graph",
        description = "Prints week graph (Tale A) for a given currency for a given period of time")
public class GraphSubComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    private boolean usageHelpRequested;

    @Override
    public void run() {
        System.out.println("Graph");
    }
}
//TODO: Define graph parameters.
//TODO: Implement graphing functionality.
