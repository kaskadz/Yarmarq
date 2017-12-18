package com.yarmarq.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yarmarq.exception.*;
import com.yarmarq.serializable.Gold;
import com.yarmarq.serializable.Rate;
import com.yarmarq.serializable.Table;
import javafx.util.Pair;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// Facade - Provide a unified interface to a set of interfaces in a subsystem. Facade defines a higher-level interface that makes the subsystem easier to use.
public class NBPApiFacade {

    public Rate getCurrentRate(String code) throws JsonParserException, OnlineResourcesAccessException {
        // http://api.nbp.pl/api/exchangerates/rates/a/{code}/
        String url = "http://api.nbp.pl/api/exchangerates/rates/a/%s/"; // code
        ObjectMapper mapper = new ObjectMapper();
        try {
            OnlineResourceFetcher fetcher = new OnlineResourceFetcher(String.format(url, code));
            if (fetcher.fetchResource()) {
                return mapper.readValue(fetcher.getContent(), Rate.class);
            } else {
                throw new OnlineResourcesAccessException(fetcher.getResponseCode(), fetcher.getResponseMessage(), fetcher.getUrl());
            }
        } catch (IOException e) {
            throw new JsonParserException(e);
        }
    }

    public Rate getRate(String code, LocalDate date) throws JsonParserException, OnlineResourcesAccessException {
        // http://api.nbp.pl/api/exchangerates/rates/a/{code}/
        String url = "http://api.nbp.pl/api/exchangerates/rates/a/%s/%s/"; // code, date
        ObjectMapper mapper = new ObjectMapper();
        try {
            OnlineResourceFetcher fetcher = new OnlineResourceFetcher(String.format(url, code, date));
            if (fetcher.fetchResource()) {
                return mapper.readValue(fetcher.getContent(), Rate.class);
            } else {
                throw new OnlineResourcesAccessException(fetcher.getResponseCode(), fetcher.getResponseMessage(), fetcher.getUrl());
            }
        } catch (IOException e) {
            throw new JsonParserException(e);
        }
    }

    public List<Rate> getRates(String code, LocalDate startDate, LocalDate endDate) throws JsonParserException, OnlineResourcesAccessException {
        // http://api.nbp.pl/api/exchangerates/rates/a/{code}/{startDate}/{endDate}/
        String url = "http://api.nbp.pl/api/exchangerates/rates/a/%s/%s/%s/"; // code, startDate, endDate
        ObjectMapper mapper = new ObjectMapper();
        List<Rate> result = new LinkedList<>();
        List<Pair<LocalDate, LocalDate>> periods = generatePeriods(startDate, endDate, 367);
        try {
            for (Pair<LocalDate, LocalDate> period : periods) {
                OnlineResourceFetcher fetcher = new OnlineResourceFetcher(String.format(url, code, period.getKey(), period.getValue()));
                if (fetcher.fetchResource()) {
                    Rate rate = mapper.readValue(fetcher.getContent(), Rate.class);
                    result.add(rate);
                } else {
                    throw new OnlineResourcesAccessException(fetcher.getResponseCode(), fetcher.getResponseMessage(), fetcher.getUrl());
                }
            }
        } catch (IOException e) {
            throw new JsonParserException(e);
        }
        return result;
    }

    public Gold getGold() throws JsonParserException, OnlineResourcesAccessException {
        // http://api.nbp.pl/api/cenyzlota
        String url = "http://api.nbp.pl/api/cenyzlota";
        ObjectMapper mapper = new ObjectMapper();
        try {
            OnlineResourceFetcher fetcher = new OnlineResourceFetcher(url);
            if (fetcher.fetchResource()) {
                Gold[] golds = mapper.readValue(fetcher.getContent(), Gold[].class);
                return golds[0];
            } else {
                throw new OnlineResourcesAccessException(fetcher.getResponseCode(), fetcher.getResponseMessage(), fetcher.getUrl());
            }
        } catch (IOException e) {
            throw new JsonParserException(e);
        }
    }

    public List<Gold> getGolds(LocalDate startDate, LocalDate endDate) throws OnlineResourcesAccessException, JsonParserException {
        // http://api.nbp.pl/api/cenyzlota/{startDate}/{endDate}
        String url = "http://api.nbp.pl/api/cenyzlota/%s/%s/"; // startDate, endDate
        ObjectMapper mapper = new ObjectMapper();
        List<Gold> result = new LinkedList<>();
        List<Pair<LocalDate, LocalDate>> periods = generatePeriods(startDate, endDate, 367);
        try {
            for (Pair<LocalDate, LocalDate> period : periods) {
                OnlineResourceFetcher fetcher = new OnlineResourceFetcher(String.format(url, period.getKey(), period.getValue()));
                if (fetcher.fetchResource()) {
                    Gold[] golds = mapper.readValue(fetcher.getContent(), Gold[].class);
                    result.addAll(Arrays.asList(golds));
                } else {
                    throw new OnlineResourcesAccessException(fetcher.getResponseCode(), fetcher.getResponseMessage(), fetcher.getUrl());
                }
            }
        } catch (IOException e) {
            throw new JsonParserException(e);
        }
        return result;
    }

    public Table getTable(char table, LocalDate date) throws JsonParserException, OnlineResourcesAccessException {
        // http://api.nbp.pl/api/exchangerates/tables/{table}/{date}/
        String url = "http://api.nbp.pl/api/exchangerates/tables/%c/%s/"; // table, date
        ObjectMapper mapper = new ObjectMapper();
        try {
            OnlineResourceFetcher fetcher = new OnlineResourceFetcher(String.format(url, table, date));
            if (fetcher.fetchResource()) {
                Table[] tables = mapper.readValue(fetcher.getContent(), Table[].class);
                return tables[0];
            } else {
                throw new OnlineResourcesAccessException(fetcher.getResponseCode(), fetcher.getResponseMessage(), fetcher.getUrl());
            }
        } catch (IOException e) {
            throw new JsonParserException(e);
        }
    }

    public List<Table> getTables(char table, LocalDate startDate, LocalDate endDate) throws OnlineResourcesAccessException, JsonParserException {
        // http://api.nbp.pl/api/exchangerates/tables/{table}/{startDate}/{endDate}/
        String url = "http://api.nbp.pl/api/exchangerates/tables/%c/%s/%s/"; // table, startDate, endDate
        ObjectMapper mapper = new ObjectMapper();
        List<Table> result = new LinkedList<>();
        List<Pair<LocalDate, LocalDate>> periods = generatePeriods(startDate, endDate, 93);
        try {
            for (Pair<LocalDate, LocalDate> period : periods) {
                OnlineResourceFetcher fetcher = new OnlineResourceFetcher(String.format(url, table, period.getKey(), period.getValue()));
                if (fetcher.fetchResource()) {
                    Table[] tables = mapper.readValue(fetcher.getContent(), Table[].class);
                    result.addAll(Arrays.asList(tables));
                } else {
                    throw new OnlineResourcesAccessException(fetcher.getResponseCode(), fetcher.getResponseMessage(), fetcher.getUrl());
                }
            }
        } catch (IOException e) {
            throw new JsonParserException(e);
        }
        return result;
    }

    private List<Pair<LocalDate, LocalDate>> generatePeriods(LocalDate startDate, LocalDate endDate, int maxPeriodDays) {
        List<Pair<LocalDate, LocalDate>> result = new LinkedList<>();
        Period period = Period.ofDays(maxPeriodDays);
        while (ChronoUnit.DAYS.between(startDate, endDate) > maxPeriodDays) {
            LocalDate tempEndDate = startDate.plus(period);
            result.add(new Pair<>(startDate, tempEndDate));
            startDate = tempEndDate.plusDays(1);
        }
        result.add(new Pair<>(startDate, endDate));
        return result;
    }

    public static void main(String[] args) {
        NBPApiFacade facade = new NBPApiFacade();
//        System.out.println(facade.getGold());
//        System.out.println(facade.getCurrentRate("gbp"));
//        System.out.println(facade.getTable("a", LocalDate.parse("2017-12-15")));
//        facade
//                .generatePeriods(LocalDate.of(2013, 1, 2), LocalDate.of(2014, 1, 5))
//                .forEach(x -> System.out.println(String.format("%s : %s [%d]", x.getKey(), x.getValue(), ChronoUnit.DAYS.between(x.getKey(), x.getValue()))));
        try {
            System.out.println("=====================");
            facade.getGolds(LocalDate.of(2013, 1, 2), LocalDate.now()).forEach(System.out::println);
            System.out.println("=====================");
            facade.getRates("gbp", LocalDate.of(2002, 1, 2), LocalDate.now()).forEach(System.out::println);
            System.out.println("=====================");
            facade.getTables('a', LocalDate.of(2002, 1, 1), LocalDate.now()).forEach(System.out::println);
        } catch (JsonParserException e) {
            e.printStackTrace();
        } catch (OnlineResourcesAccessException e) {
            e.getMessage();
        }
    }
}
