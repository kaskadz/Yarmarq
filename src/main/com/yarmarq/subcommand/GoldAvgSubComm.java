package com.yarmarq.subcommand;

import picocli.CommandLine.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Command(name = "gold-avg",
        description = "Calculates average price of gold in a given period of time.")
public class GoldAvgSubComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    private boolean usageHelpRequested;

    @Parameters(index = "0", arity = "1", paramLabel = "START_DATE",
            description = "Beginning of time period.")
    private Date basicStartDate;
    private LocalDate startDate;

    @Parameters(index = "1", arity = "1", paramLabel = "END_DATE",
            description = "End of time period.")
    private Date basicEndDate;
    private LocalDate endDate;

    private void preRun() {
        startDate = LocalDate.ofInstant(basicStartDate.toInstant(), ZoneId.systemDefault());
        endDate = LocalDate.ofInstant(basicEndDate.toInstant(), ZoneId.systemDefault());
    }

    @Override
    public void run() {
        System.out.println("Gold");
        System.out.println(startDate);
        System.out.println(endDate);
    }
}
