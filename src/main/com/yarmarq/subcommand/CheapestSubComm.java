package com.yarmarq.subcommand;

import picocli.CommandLine.*;

@Command(name = "cheapest",
        description = "Finds the cheapest currency (Table C) in a given day.")
public class CheapestSubComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    private boolean usageHelpRequested;

    @Override
    public void run() {
        System.out.println("Cheapest");
    }
}
