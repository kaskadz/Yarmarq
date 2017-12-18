package com.yarmarq.subcommand;

import picocli.CommandLine.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Command(name = "spread",
        description = "Finds n currencies (Table C), sorted by spread in a given day.")
public class SpreadSubComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    private boolean usageHelpRequested;

    @Parameters(index = "0", arity = "1", paramLabel = "DATE",
            description = "Date to calculate spread from.")
    private Date basicDate;
    private LocalDate date;

    @Parameters(index = "1", arity = "1", paramLabel = "N",
            description = "How many currencies to find.")
    private Integer n;

    private void preRun() {
        date = LocalDate.ofInstant(basicDate.toInstant(), ZoneId.systemDefault());
    }

    @Override
    public void run() {
        System.out.println("Spread");
        System.out.println(date);
        System.out.println(n);
    }
}
//TODO: Implement spread functionality.
