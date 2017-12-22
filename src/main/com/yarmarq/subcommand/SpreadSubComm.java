package com.yarmarq.subcommand;

import com.yarmarq.converter.RateLocalDateTypeConverter;
import com.yarmarq.deserializable.TRate;
import com.yarmarq.deserializable.Table;
import com.yarmarq.exception.JsonParserException;
import com.yarmarq.exception.OnlineResourcesAccessException;
import com.yarmarq.module.NBPApiFacade;
import picocli.CommandLine.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

@Command(name = "spread",
        description = "Finds N currencies (Table C), sorted by spread in a given day.")
public class SpreadSubComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    private boolean usageHelpRequested;

    @Option(names = {"-d", "--desc"},
            description = "Sorts in descending order. (Default is ascending)")
    private boolean desc;

    @Parameters(index = "0", arity = "1", paramLabel = "DATE",
            description = "Date to calculate spread from.",
            converter = RateLocalDateTypeConverter.class)
    private LocalDate date;

    @Parameters(index = "1", arity = "1", paramLabel = "N",
            description = "How many currencies to find.")
    private Integer n;

    @Override
    public void run() {
        try {
            NBPApiFacade facade = NBPApiFacade.getInstance();
            Table table = facade.getTable('c', date);
            Stream<TRate> str = Arrays.stream(table.getRates());
            if (desc) str = str.sorted(Comparator.comparing(TRate::getSpread));
            else str = str.sorted(Comparator.comparing(TRate::getSpread).reversed());
            str
                    .limit(n)
                    .forEach(System.out::println);
        } catch (JsonParserException e) {
            e.printStackTrace();
        } catch (OnlineResourcesAccessException e) {
            System.out.println("An error occurred!");
            System.out.println(e.getMessage());
        }
    }
}
// DONE
