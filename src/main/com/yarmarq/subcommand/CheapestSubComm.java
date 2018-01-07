package com.yarmarq.subcommand;

import com.yarmarq.AbstractCommand;
import com.yarmarq.converter.RateLocalDateTypeConverter;
import com.yarmarq.exception.JsonParserException;
import com.yarmarq.exception.OnlineResourcesAccessException;
import com.yarmarq.module.NBPApiFacade;
import com.yarmarq.deserializable.Table;
import com.yarmarq.task.SmallestBuyingRateTask;
import com.yarmarq.task.TaskManager;
import picocli.CommandLine.*;

import java.time.LocalDate;

@Command(
        name = "cheapest",
        description = "Finds currency (Table C), that was had the smallest buying rate in a given day."
)
public class CheapestSubComm extends AbstractCommand implements Runnable {

    @Parameters(
            index = "0",
            arity = "0..1",
            paramLabel = "DATE",
            description = "Date when to look for currency with the cheapest buying rate.",
            converter = RateLocalDateTypeConverter.class
    )
    private LocalDate date;

    @Override
    public void run() {
        printBanner();
        try {
            NBPApiFacade facade = NBPApiFacade.getInstance();
            Table table;
            printPreDownloadMessage();
            if (date == null) {
                table = facade.getTable('c');
            } else {
                table = facade.getTable('c', date);
            }
            printPostDownloadMessage();
            TaskManager taskManager = new TaskManager();
            taskManager.addTaskAndAccomplishAll(new SmallestBuyingRateTask(table));
        } catch (JsonParserException | OnlineResourcesAccessException e) {
            printGeneralErrorMessage();
            System.out.println(e.getMessage());
        }
    }
}
