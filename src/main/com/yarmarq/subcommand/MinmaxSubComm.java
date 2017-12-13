package com.yarmarq.subcommand;

import picocli.CommandLine.*;

@Command(name = "minmax",
        description = "For a given currency in a table A prints info, about the lowest and the highest exchange rate.")
public class MinmaxSubComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    private boolean usageHelpRequested;

    @Parameters(index = "0", arity = "1", paramLabel = "CODE",
            description = "Currency code.")
    private String code;

    @Override
    public void run() {
        System.out.println("Minmax");
        System.out.println(code);
    }
}
