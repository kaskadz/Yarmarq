package com.yarmarq.subcommand;

import com.yarmarq.AbstractCommand;
import com.yarmarq.converter.GoldLocalDateTypeConverter;
import com.yarmarq.deserializable.Gold;
import com.yarmarq.exception.JsonParserException;
import com.yarmarq.exception.OnlineResourcesAccessException;
import com.yarmarq.module.NBPApiFacade;
import com.yarmarq.task.GoldPriceTask;
import com.yarmarq.task.ITask;
import com.yarmarq.task.TaskManager;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.time.LocalDate;

@Command(
        name = "gold",
        description = "Prints gold price in a given day."
)
public class GoldSubComm extends AbstractCommand implements Runnable {

    @Parameters(
            index = "0",
            arity = "0..1",
            paramLabel = "DATE",
            description = "Date of gold price.",
            converter = GoldLocalDateTypeConverter.class
    )
    private LocalDate date;

    @Override
    public void run() {
        printBanner();
        try {
            NBPApiFacade facade = NBPApiFacade.getInstance();
            Gold gold;
            printPreDownloadMessage();
            if (date == null) {
                gold = facade.getGold();
            } else {
                gold = facade.getGold(date);
            }
            printPostDownloadMessage();
            TaskManager taskManager = new TaskManager();
            taskManager.addTaskAndAcomplishAll(new GoldPriceTask(gold));
        } catch (JsonParserException e) {
            e.printStackTrace();
        } catch (OnlineResourcesAccessException e) {
            printGeneralErrorMessage();
            System.out.println(e.getMessage());
        }
    }
}
