package com.yarmarq.subcommand;

import com.yarmarq.AbstractCommand;
import com.yarmarq.converter.RateDatePeriodTypeConverter;
import com.yarmarq.module.DatePeriod;
import picocli.CommandLine.*;

@Command(
        name = "graph",
        description = "Prints week graph (Tale A) for a given currency for a given period of time"
)
public class GraphSubComm extends AbstractCommand implements Runnable {

    @Parameters(
            index = "0",
            arity = "1",
            paramLabel = "CODE",
            description = "Currency code."
    )
    private String code;

    @Parameters(
            index = "1",
            arity = "1",
            paramLabel = "DATE_PERIOD",
            description = "Date period, to generate graph from.",
            converter = RateDatePeriodTypeConverter.class
    )
    private DatePeriod period;

    @Override
    public void run() {
        System.out.println("Graph");
        System.out.println(period);
    }
}
//TODO: Implement graphing functionality.
