package com.yarmarq.subcommand;

import com.yarmarq.exception.*;
import com.yarmarq.module.NBPApiFacade;
import com.yarmarq.serializable.Rate;
import picocli.CommandLine.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Command(name = "price",
        description = "Tells current price of goldPrice and current exchange rate of a given currency (Table A) in a given day.")
public class PriceSubComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    private boolean usageHelpRequested;

    @Parameters(index = "0", arity = "1", paramLabel = "CODE",
            description = "Currency code.")
    private String code;

    @Parameters(index = "1", arity = "0..1", paramLabel = "DATE",
            description = "Exchange rate publication date.")
    private Date basicDate;
    private LocalDate date;

    private void preRun() {
        if (basicDate != null) date = LocalDate.ofInstant(basicDate.toInstant(), ZoneId.systemDefault());
    }

    @Override
    public void run() {
        preRun();
//        System.out.println("Price");
//        System.out.println(date);
//        System.out.println(code);
        NBPApiFacade facade = NBPApiFacade.getInstance();
        try {
            Rate rate;
            if (date == null) {
                rate = facade.getCurrentRate(code);
            } else {
                rate = facade.getRate(code, date);
            }
            System.out.println(rate.getCode());
            System.out.println(rate.getRates()[0]);
        } catch (JsonParserException e) {
            e.printStackTrace();
        } catch (OnlineResourcesAccessException e) {
            System.out.println("An error occurred!");
            System.out.println(e.getMessage());
        }
    }
}
