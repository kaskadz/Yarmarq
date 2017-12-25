package com.yarmarq.subcommand;

import com.yarmarq.AbstractCommand;
import com.yarmarq.converter.RateLocalDateTypeConverter;
import com.yarmarq.deserializable.Table;
import com.yarmarq.exception.JsonParserException;
import com.yarmarq.exception.OnlineResourcesAccessException;
import com.yarmarq.exception.WrongDatePeriodException;
import com.yarmarq.module.DatePeriod;
import com.yarmarq.module.NBPApiFacade;
import com.yarmarq.task.ITask;
import com.yarmarq.task.MaxFluctuationsCurrencyTask;
import com.yarmarq.task.TaskManager;
import picocli.CommandLine.*;

import java.time.LocalDate;
import java.util.*;

@Command(
        name = "fluctuations",
        description = "Finds a currency in table A, which exchange rate, from a given date, fluctuated the most."
)
public class FluctuationsSubComm extends AbstractCommand implements Runnable {

    @Parameters(
            index = "0",
            arity = "1",
            paramLabel = "DATE",
            description = "Date, from which to calculate fluctuations.",
            converter = RateLocalDateTypeConverter.class
    )
    private LocalDate date;

    @Override
    public void run() {
        printBanner();
        try {
            NBPApiFacade facade = NBPApiFacade.getInstance();
            printPreDownloadMessage();
            List<Table> tables = facade.getTables('a', new DatePeriod(date, LocalDate.now()));
            printPostDownloadMessage();
            TaskManager taskManager = new TaskManager();
            taskManager.addTaskAndAcomplishAll(new MaxFluctuationsCurrencyTask(tables, date));
        } catch (OnlineResourcesAccessException e) {
            printGeneralErrorMessage();
            System.out.println(e.getMessage());
        } catch (JsonParserException | WrongDatePeriodException e) {
            e.printStackTrace();
        }
    }
}
// DONE