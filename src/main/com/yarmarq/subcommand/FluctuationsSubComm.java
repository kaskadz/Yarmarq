package com.yarmarq.subcommand;

import picocli.CommandLine.*;

@Command(name = "fluctuations",
        description = "Finds a currency in table A, which exchange rate, from a given date, fluctuated the most.")
public class FluctuationsSubComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    private boolean usageHelpRequested;

    @Override
    public void run() {
        System.out.println("Fluctuations");
    }
}
