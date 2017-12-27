package com.yarmarq.subcommand;

import com.yarmarq.AbstractCommand;
import com.yarmarq.converter.CurrencyCodeTypeConverter;
import com.yarmarq.converter.GoldLocalDateTypeConverter;
import com.yarmarq.converter.RateLocalDateTypeConverter;
import com.yarmarq.deserializable.Gold;
import com.yarmarq.deserializable.Rate;
import com.yarmarq.exception.JsonParserException;
import com.yarmarq.exception.OnlineResourcesAccessException;
import com.yarmarq.module.NBPApiFacade;
import com.yarmarq.task.CurrencyExchangeRateTask;
import com.yarmarq.task.GoldPriceTask;
import com.yarmarq.task.TaskManager;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;

import java.time.LocalDate;

@Command(
        name = "gold-and-rates",
        description = "Tells price of gold and exchange rate of a given currency (Table A) in a given day."
)
public class GoldAndRateSubComm extends AbstractCommand implements Runnable {
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
            description = "Exchange rate and gold price publication date. (If not given, the most recent data is used.",
            converter = GoldLocalDateTypeConverter.class
    )
    private LocalDate date;

    @Override
    public void run() {
        printBanner();
        try {
            NBPApiFacade facade = NBPApiFacade.getInstance();
            TaskManager taskManager = new TaskManager();
            Rate rate;
            printPreDownloadMessage();
            if (date == null) {
                rate = facade.getRate(code);
            } else {
                rate = facade.getRate(code, date);
            }
            taskManager.addTask(new CurrencyExchangeRateTask(rate));
            Gold gold;
            if (date == null) {
                gold = facade.getGold();
            } else {
                gold = facade.getGold(date);
            }
            printPostDownloadMessage();
            taskManager.addTask(new GoldPriceTask(gold));
            taskManager.acomplishTasks();
        } catch (JsonParserException e) {
            e.printStackTrace();
        } catch (OnlineResourcesAccessException e) {
            printGeneralErrorMessage();
            System.out.println(e.getMessage());
        }
    }
}
