package com.yarmarq.subcommand;

import com.yarmarq.module.DateFormatter;
import picocli.CommandLine.*;

import java.util.Date;

@Command(name = "gold",
        description = "Calculates average price of gold in a given period of time.")
public class GoldSubComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    private boolean usageHelpRequested;

    @Parameters(index = "0", arity = "1", paramLabel = "START_DATE",
            description = "Beginning of time period.")
    private Date startDate;

    @Parameters(index = "1", arity = "1", paramLabel = "END_DATE",
            description = "End of time period.")
    private Date endDate;

    @Override
    public void run() {
        System.out.println("Gold");
        System.out.println(DateFormatter.formatDate(startDate));
        System.out.println(DateFormatter.formatDate(endDate));
    }
}
