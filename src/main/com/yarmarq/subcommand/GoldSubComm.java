package com.yarmarq.subcommand;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Command(name = "gold",
        description = "Calculates average price of gold in a given period of time.")
public class GoldSubComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    private boolean usageHelpRequested;

    private void preRun() {
    }

    @Override
    public void run() {
        System.out.println("Gold");
    }
}
