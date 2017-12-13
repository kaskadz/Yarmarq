package com.yarmarq.subcommand;

import com.yarmarq.module.DateFormatter;
import picocli.CommandLine.*;

import java.util.Date;

@Command(name = "cheapest",
        description = "Finds the cheapest currency (Table C) in a given day.")
public class CheapestSubComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    private boolean usageHelpRequested;

    @Parameters(index = "0", arity = "1", paramLabel = "DATE",
            description = "Date when to look for the cheapest currency.")
    private Date date;

    @Override
    public void run() {
        System.out.println("Cheapest");
        System.out.println(DateFormatter.formatDate(date));
    }
}
