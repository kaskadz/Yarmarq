package com.yarmarq.subcommand;

import com.yarmarq.AbstractCommand;
import com.yarmarq.chart.IChart;
import com.yarmarq.chart.TextWeekRRateChart;
import com.yarmarq.converter.CurrencyCodeTypeConverter;
import com.yarmarq.converter.RateWeekDatePeriodTypeConverter;
import com.yarmarq.deserializable.Rate;
import com.yarmarq.exception.JsonParserException;
import com.yarmarq.exception.OnlineResourcesAccessException;
import com.yarmarq.module.DatePeriod;
import com.yarmarq.module.NBPApiFacade;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
        name = "graph",
        description = "Prints week graph (Tale A) for a given currency for a given period of time"
)
public class GraphSubComm extends AbstractCommand implements Runnable {

    @Parameters(
            index = "0",
            arity = "1",
            paramLabel = "CODE",
            description = "Currency code.",
            converter = CurrencyCodeTypeConverter.class
    )
    private String code;

    @Parameters(
            index = "1",
            arity = "1",
            paramLabel = "DATE_PERIOD",
            description = "Date period, to generate graph from. Format: yyyy-MM,n-yyyy-MM,n",
            converter = RateWeekDatePeriodTypeConverter.class
    )
    private DatePeriod period;

    @Option(
            names = {"-w", "--width"},
            paramLabel = "WIDTH",
            arity = "1",
            description = "Max chart bar width."
    )
    private int maxBarWidth = 100;

    @Option(
            names = {"-c", "--char"},
            paramLabel = "C",
            arity = "1",
            description = "Char, to create chat bars from."
    )
    private char barChar = '#';

    @Override
    public void run() {
        printBanner();
        try {
            NBPApiFacade facade = NBPApiFacade.getInstance();
            printPreDownloadMessage();
            Rate rates = facade.getRates(code, period);
            printPostDownloadMessage();
            IChart chart = new TextWeekRRateChart(rates, maxBarWidth, barChar);
            chart.draw();
        } catch (JsonParserException e) {
            e.printStackTrace();
        } catch (OnlineResourcesAccessException e) {
            printGeneralErrorMessage();
            System.out.println(e.getMessage());
        }
    }
}
