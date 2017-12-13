package com.yarmarq.subcommand;

import com.yarmarq.module.DateFormatter;
import picocli.CommandLine.*;

import java.util.Date;

@Command(name = "price",
        description = "Tells current price of gold and current exchange rate of a given currency (Table A) in a given day.")
public class PriceSumComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    private boolean usageHelpRequested;

    @Parameters(index = "0", paramLabel = "DATE", description = "Exchange rate publication date.", arity = "1")
    private Date date;

    @Parameters(index = "1", paramLabel = "CODE", description = "Currency code.", arity = "0..1")
    private String currency;

    @Override
    public void run() {
        System.out.println("Price");
        System.out.println(DateFormatter.formatDate(date));
        System.out.println(currency);
    }
}
