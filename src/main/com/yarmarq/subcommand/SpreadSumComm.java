package com.yarmarq.subcommand;

import picocli.CommandLine.*;

@Command(name = "spread",
        description = "Finds n currencies (Table C), sorted by spread in a given day.")
public class SpreadSumComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    private boolean usageHelpRequested;

    @Override
    public void run() {
        System.out.println("Spread");
    }
}
