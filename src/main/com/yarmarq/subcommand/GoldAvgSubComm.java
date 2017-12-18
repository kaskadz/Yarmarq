package com.yarmarq.subcommand;

import com.yarmarq.exception.JsonParserException;
import com.yarmarq.exception.OnlineResourcesAccessException;
import com.yarmarq.exception.WrongTimePeriodException;
import com.yarmarq.module.NBPApiFacade;
import com.yarmarq.serializable.Gold;
import picocli.CommandLine.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Command(name = "gold-avg",
        description = "Calculates average price of gold in a given period of time.")
public class GoldAvgSubComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    private boolean usageHelpRequested;

    @Parameters(index = "0", arity = "1", paramLabel = "START_DATE",
            description = "Beginning of time period.")
    private Date basicStartDate;
    private LocalDate startDate;

    @Parameters(index = "1", arity = "1", paramLabel = "END_DATE",
            description = "End of time period.")
    private Date basicEndDate;
    private LocalDate endDate;

    private void preRun() throws WrongTimePeriodException {
        if (basicStartDate != null && basicEndDate != null) {
            startDate = LocalDate.ofInstant(basicStartDate.toInstant(), ZoneId.systemDefault());
            endDate = LocalDate.ofInstant(basicEndDate.toInstant(), ZoneId.systemDefault());
            if (startDate.isAfter(endDate)) throw new WrongTimePeriodException();
        }
    }

    @Override
    public void run() {
        try {
            preRun();
            NBPApiFacade facade = NBPApiFacade.getInstance();
            List<Gold> golds = facade.getGolds(startDate, endDate);
            Double avg = golds.stream().mapToDouble(x -> x.getPrice()).average().getAsDouble();
            System.out.printf("Gold average price from %s to %s is: %f", startDate, endDate, avg);
        } catch (WrongTimePeriodException e) {
            System.out.println(e.getMessage());
        } catch (JsonParserException e) {
            e.printStackTrace();
        } catch (OnlineResourcesAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
