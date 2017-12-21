package com.yarmarq.subcommand;

import com.yarmarq.exception.JsonParserException;
import com.yarmarq.exception.OnlineResourcesAccessException;
import com.yarmarq.module.NBPApiFacade;
import com.yarmarq.deserializable.TRate;
import com.yarmarq.deserializable.Table;
import picocli.CommandLine.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

@Command(name = "cheapest",
        description = "Finds currency (Table C), that was had the smallest buying rate in a given day.")
public class CheapestSubComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    private boolean usageHelpRequested;

    @Parameters(index = "0", arity = "0..1", paramLabel = "DATE",
            description = "Date when to look for currency with the cheapest buying rate.")
    private Date basicDate;
    private LocalDate date;

    private void preRun() {
        if (basicDate != null) {
            date = LocalDate.ofInstant(basicDate.toInstant(), ZoneId.systemDefault());
        }
    }

    @Override
    public void run() {
        preRun();
        NBPApiFacade facade = NBPApiFacade.getInstance();
        Table table;
        try {
            if (date == null) {
                table = facade.getTable('c');
            } else {
                table = facade.getTable('c', date);
            }
            TRate rate = Arrays.stream(table.getRates()).min(Comparator.comparing(TRate::getBid)).get();
            System.out.printf("Currency %s of code %s had the smallest buying rate in %s, which was %f", rate.getCurrency(), rate.getCode(), table.getEffectiveDate(), rate.getBid());
        } catch (JsonParserException e) {
            e.printStackTrace();
        } catch (OnlineResourcesAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
// DONE