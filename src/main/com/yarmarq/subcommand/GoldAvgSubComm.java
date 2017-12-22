package com.yarmarq.subcommand;

import com.yarmarq.converter.GoldDatePeriodTypeConverter;
import com.yarmarq.converter.GoldLocalDateTypeConverter;
import com.yarmarq.exception.DateFromTheFutureException;
import com.yarmarq.exception.JsonParserException;
import com.yarmarq.exception.OnlineResourcesAccessException;
import com.yarmarq.exception.WrongDatePeriodException;
import com.yarmarq.module.DatePeriod;
import com.yarmarq.module.NBPApiFacade;
import com.yarmarq.deserializable.Gold;
import picocli.CommandLine.*;

import java.time.LocalDate;
import java.util.List;

@Command(name = "gold-avg",
        description = "Calculates average price of gold in a given period of time.")
public class GoldAvgSubComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    private boolean usageHelpRequested;

    @Parameters(index = "0", arity = "1", paramLabel = "TIME_PERIOD",
            description = "Time period in yyyy-MM-dd:yyyy-MM-dd format.",
            converter = GoldDatePeriodTypeConverter.class)
    private DatePeriod period;

    @Override
    public void run() {
        try {
            NBPApiFacade facade = NBPApiFacade.getInstance();
            List<Gold> golds = facade.getGolds(period);
            Double avg = golds.stream().mapToDouble(x -> x.getPrice()).average().getAsDouble();
            System.out.printf("Gold average price from %s to %s is: %f", period.getStartDate(), period.getEndDate(), avg);
        } catch (JsonParserException e) {
            e.printStackTrace();
        } catch (OnlineResourcesAccessException e) {
            System.out.println("An error occurred!");
            System.out.println(e.getMessage());
        }
    }
}
// DONE