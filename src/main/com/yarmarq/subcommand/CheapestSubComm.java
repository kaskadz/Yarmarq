package com.yarmarq.subcommand;

import com.yarmarq.AbstractCommand;
import com.yarmarq.converter.RateLocalDateTypeConverter;
import com.yarmarq.exception.JsonParserException;
import com.yarmarq.exception.OnlineResourcesAccessException;
import com.yarmarq.module.NBPApiFacade;
import com.yarmarq.deserializable.TRate;
import com.yarmarq.deserializable.Table;
import picocli.CommandLine.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

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
        try {
            NBPApiFacade facade = NBPApiFacade.getInstance();
            Table table;
            if (date == null) {
                table = facade.getTable('c');
            } else {
                table = facade.getTable('c', date);
            }
            TRate rate = Arrays.stream(table.getRates()).min(Comparator.comparing(TRate::getBid)).orElseThrow(RuntimeException::new);
            System.out.printf("Currency %s of code %s had the smallest buying rate in %s, which was %f", rate.getCurrency(), rate.getCode(), table.getEffectiveDate(), rate.getBid());
        } catch (JsonParserException e) {
            e.printStackTrace();
        } catch (OnlineResourcesAccessException e) {
            System.out.println("An error occurred!");
            System.out.println(e.getMessage());
        }
    }
}
// DONE