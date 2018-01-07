package com.yarmarq.subcommand;

import com.yarmarq.AbstractCommand;
import com.yarmarq.converter.GoldDatePeriodTypeConverter;
import com.yarmarq.deserializable.Gold;
import com.yarmarq.exception.JsonParserException;
import com.yarmarq.exception.OnlineResourcesAccessException;
import com.yarmarq.module.DatePeriod;
import com.yarmarq.module.NBPApiFacade;
import com.yarmarq.task.AvgGoldPriceTask;
import com.yarmarq.task.TaskManager;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.util.List;

@Command(
        name = "gold-avg",
        description = "Calculates average price of gold in a given period of time."
)
public class GoldAvgSubComm extends AbstractCommand implements Runnable {

    @Parameters(
            index = "0",
            arity = "1",
            paramLabel = "TIME_PERIOD",
            description = "Time period in yyyy-MM-dd:yyyy-MM-dd format.",
            converter = GoldDatePeriodTypeConverter.class
    )
    private DatePeriod period;

    @Override
    public void run() {
        printBanner();
        try {
            NBPApiFacade facade = NBPApiFacade.getInstance();
            printPreDownloadMessage();
            List<Gold> golds = facade.getGolds(period);
            printPostDownloadMessage();
            TaskManager taskManager = new TaskManager();
            taskManager.addTaskAndAccomplishAll(new AvgGoldPriceTask(golds, period));
        } catch (JsonParserException | OnlineResourcesAccessException e) {
            printGeneralErrorMessage();
            System.out.println(e.getMessage());
        }
    }
}
