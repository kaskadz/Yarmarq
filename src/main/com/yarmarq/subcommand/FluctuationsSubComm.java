package com.yarmarq.subcommand;

import picocli.CommandLine.*;

import java.time.LocalDate;

@Command(name = "fluctuations",
        description = "Finds a currency in table A, which exchange rate, from a given date, fluctuated the most.")
public class FluctuationsSubComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    private boolean usageHelpRequested;

    @Parameters(index = "0", arity = "1", paramLabel = "DATE",
            description = "Date, from which to calculate fluctuations.")
    private LocalDate date;

    @Parameters(index = "1", arity = "1", paramLabel = "CODE",
            description = "Currency code.")
    private String code;

    @Override
    public void run() {
        System.out.println("Fluctuations");
        System.out.println(date);
        System.out.println(code);
    }
}
