package com.yarmarq.subcommand;

import picocli.CommandLine.*;

import java.time.LocalDate;
import java.util.Date;

@Command(name = "price",
        description = "Tells current price of gold and current exchange rate of a given currency (Table A) in a given day.")
public class PriceSubComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    private boolean usageHelpRequested;

    @Parameters(index = "0", arity = "1", paramLabel = "DATE",
            description = "Exchange rate publication date.")
    private Date basicDate;
    private LocalDate date;

    @Parameters(index = "1", arity = "0..1", paramLabel = "CODE",
            description = "Currency code.")
    private String code;

    @Override
    public void run() {
        System.out.println("Price");
        System.out.println(date);
        System.out.println(code);
    }
}
