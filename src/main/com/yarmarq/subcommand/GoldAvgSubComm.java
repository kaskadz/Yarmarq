package com.yarmarq.subcommand;

import com.yarmarq.AbstractCommand;
import com.yarmarq.converter.GoldDatePeriodTypeConverter;
import com.yarmarq.deserializable.Gold;
import com.yarmarq.exception.JsonParserException;
import com.yarmarq.exception.OnlineResourcesAccessException;
import com.yarmarq.module.DatePeriod;
import com.yarmarq.module.NBPApiFacade;
import com.yarmarq.task.AvgGoldPriceTask;
import com.yarmarq.task.ITask;
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
        try {
            NBPApiFacade facade = NBPApiFacade.getInstance();
            List<Gold> golds = facade.getGolds(period);
            ITask task = new AvgGoldPriceTask(golds, period);
            task.accomplish();
        } catch (JsonParserException e) {
            e.printStackTrace();
        } catch (OnlineResourcesAccessException e) {
            System.out.println("An error occurred!");
            System.out.println(e.getMessage());
        }
    }
}
// DONE