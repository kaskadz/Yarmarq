package com.yarmarq.subcommand;

import com.yarmarq.AbstractCommand;
import com.yarmarq.converter.CurrencyCodeTypeConverter;
import com.yarmarq.deserializable.Rate;
import com.yarmarq.exception.JsonParserException;
import com.yarmarq.exception.OnlineResourcesAccessException;
import com.yarmarq.exception.WrongDatePeriodException;
import com.yarmarq.module.DatePeriod;
import com.yarmarq.module.NBPApiFacade;
import com.yarmarq.task.MinmaxExchangeRateTask;
import com.yarmarq.task.TaskManager;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.time.LocalDate;

@Command(
        name = "minmax",
        description = "For a given currency in a table A prints info, when it's exchange rate was the lowest and the highest."
)
public class MinmaxSubComm extends AbstractCommand implements Runnable {

    @Parameters(
            index = "0",
            arity = "1",
            paramLabel = "CODE",
            description = "Currency code.",
            converter = CurrencyCodeTypeConverter.class
    )
    private String code;

    @Override
    public void run() {
        printBanner();
        try {
            NBPApiFacade facade = NBPApiFacade.getInstance();
            printPreDownloadMessage();
            Rate rates = facade.getRates(code, new DatePeriod(LocalDate.of(2002, 1, 2), LocalDate.now()));
            printPostDownloadMessage();
            TaskManager taskManager = new TaskManager();
            taskManager.addTaskAndAccomplishAll(new MinmaxExchangeRateTask(rates));
        } catch (JsonParserException | WrongDatePeriodException | OnlineResourcesAccessException e) {
            printGeneralErrorMessage();
            System.out.println(e.getMessage());
        }
    }
}
