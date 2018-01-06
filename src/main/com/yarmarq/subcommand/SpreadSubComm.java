package com.yarmarq.subcommand;

import com.yarmarq.AbstractCommand;
import com.yarmarq.converter.RateLocalDateTypeConverter;
import com.yarmarq.deserializable.Table;
import com.yarmarq.exception.JsonParserException;
import com.yarmarq.exception.OnlineResourcesAccessException;
import com.yarmarq.module.NBPApiFacade;
import com.yarmarq.task.CurrenciesSortedBySpreadTask;
import com.yarmarq.task.ITask;
import com.yarmarq.task.TaskManager;
import picocli.CommandLine.*;

import java.time.LocalDate;

@Command(
        name = "spread",
        description = "Finds N currencies (Table C), sorted by spread in a given day."
)
public class SpreadSubComm extends AbstractCommand implements Runnable {

    @Option(
            names = {"-a", "--asc"},
            description = "Sorts in ascending order. (Default is descending)"
    )
    private boolean asc;

    @Parameters(
            index = "0",
            arity = "1",
            paramLabel = "DATE",
            description = "Date to calculate spread from.",
            converter = RateLocalDateTypeConverter.class
    )
    private LocalDate date;

    @Parameters(
            index = "1",
            arity = "0..1",
            paramLabel = "N",
            description = "How many currencies to find. (Default 5)"
    )
    private Integer n = 5;

    @Override
    public void run() {
        printBanner();
        try {
            NBPApiFacade facade = NBPApiFacade.getInstance();
            printPreDownloadMessage();
            Table table = facade.getTable('c', date);
            printPostDownloadMessage();
            TaskManager taskManager = new TaskManager();
            taskManager.addTaskAndAcomplishAll(new CurrenciesSortedBySpreadTask(table, n, asc, date));
        } catch (JsonParserException | OnlineResourcesAccessException e) {
            printGeneralErrorMessage();
            System.out.println(e.getMessage());
        }
    }
}
