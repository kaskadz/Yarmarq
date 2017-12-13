package com.yarmarq.subcommand;

import picocli.CommandLine.*;

@Command(name = "spread")
public class SpreadSumComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    boolean usageHelpRequested;

    @Override
    public void run() {
        System.out.println("Spread");
    }
}
