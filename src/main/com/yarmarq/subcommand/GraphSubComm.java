package com.yarmarq.subcommand;

import picocli.CommandLine.*;

@Command(name = "graph",
        description = "Prints week graph (Tale A) for a given currency for a given period of time")
public class GraphSubComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    private boolean usageHelpRequested;

    @Parameters(index = "0", arity = "1", paramLabel = "CODE",
            description = "Currency code.")
    private String code;

    @Parameters(index = "1", arity = "1", paramLabel = "START_YEAR",
            description = "Start year.")
    private Integer startYear;
    @Parameters(index = "2", arity = "1", paramLabel = "START_MONTH",
            description = "Start month.")
    private Integer startMonth;
    @Parameters(index = "3", arity = "1", paramLabel = "START_WEEK",
            description = "Start week of the month.")
    private Integer startWeekNo;

    @Parameters(index = "4", arity = "1", paramLabel = "END_YEAR",
            description = "End year.")
    private Integer endYear;
    @Parameters(index = "5", arity = "1", paramLabel = "END_MONTH",
            description = "End month.")
    private Integer endMonth;
    @Parameters(index = "6", arity = "1", paramLabel = "END_WEEK",
            description = "End week of the month.")
    private Integer endWeekNo;

    @Override
    public void run() {
        System.out.println("Graph");
    }
}
//TODO: Define graph parameters.
//TODO: Implement graphing functionality.
