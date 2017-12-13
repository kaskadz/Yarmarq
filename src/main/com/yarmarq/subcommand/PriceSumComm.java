package com.yarmarq.subcommand;

import picocli.CommandLine.*;

@Command(name = "price",
        description = "Tells current price of gold and current exchange rate of a given currency (Table A).")
public class PriceSumComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    boolean usageHelpRequested;

    @Override
    public void run() {
        System.out.println("Price");
    }
}
