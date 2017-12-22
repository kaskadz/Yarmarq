package com.yarmarq.subcommand;

import com.yarmarq.converter.CurrencyCodeTypeConverter;
import com.yarmarq.converter.RateLocalDateTypeConverter;
import com.yarmarq.exception.*;
import com.yarmarq.module.NBPApiFacade;
import com.yarmarq.deserializable.Rate;
import picocli.CommandLine.*;

import java.time.LocalDate;

@Command(name = "price",
        description = "Tells current price of goldPrice and current exchange rate of a given currency (Table A) in a given day.")
public class PriceSubComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    private boolean usageHelpRequested;

    @Parameters(index = "0", arity = "1", paramLabel = "CODE",
            description = "Currency code.",
            converter = CurrencyCodeTypeConverter.class)
    private String code;

    @Parameters(index = "1", arity = "0..1", paramLabel = "DATE",
            description = "Exchange rate publication date.",
            converter = RateLocalDateTypeConverter.class)
    private LocalDate date;

    @Override
    public void run() {
        try {
            NBPApiFacade facade = NBPApiFacade.getInstance();
            Rate rate;
            if (date == null) {
                rate = facade.getRate(code);
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
// DONE