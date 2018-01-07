package com.yarmarq.subcommand;

import com.yarmarq.AbstractCommand;
import com.yarmarq.converter.CurrencyCodeTypeConverter;
import com.yarmarq.converter.RateLocalDateTypeConverter;
import com.yarmarq.exception.*;
import com.yarmarq.module.NBPApiFacade;
import com.yarmarq.deserializable.Rate;
import com.yarmarq.task.CurrencyExchangeRateTask;
import com.yarmarq.task.TaskManager;
import picocli.CommandLine.*;

import java.time.LocalDate;

@Command(
        name = "price",
        description = "Tells current price of goldPrice and current exchange rate of a given currency (Table A) in a given day."
)
public class PriceSubComm extends AbstractCommand implements Runnable {

    @Parameters(
            index = "0",
            arity = "1",
            paramLabel = "CODE",
            description = "Currency code.",
            converter = CurrencyCodeTypeConverter.class
    )
    private String code;

    @Parameters(
            index = "1",
            arity = "0..1",
            paramLabel = "DATE",
            description = "Exchange rate publication date.",
            converter = RateLocalDateTypeConverter.class
    )
    private LocalDate date;

    @Override
    public void run() {
        printBanner();
        try {
            NBPApiFacade facade = NBPApiFacade.getInstance();
            Rate rate;
            printPreDownloadMessage();
            if (date == null) {
                rate = facade.getRate(code);
            } else {
                rate = facade.getRate(code, date);
            }
            printPostDownloadMessage();
            TaskManager taskManager = new TaskManager();
            taskManager.addTaskAndAccomplishAll(new CurrencyExchangeRateTask(rate));
        } catch (JsonParserException | OnlineResourcesAccessException e) {
            printGeneralErrorMessage();
            System.out.println(e.getMessage());
        }
    }
}
