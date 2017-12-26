package com.yarmarq.module;

import com.yarmarq.deserializable.Gold;
import com.yarmarq.deserializable.RRate;
import com.yarmarq.deserializable.Rate;
import com.yarmarq.deserializable.Table;
import com.yarmarq.exception.JsonParserException;
import com.yarmarq.exception.OnlineResourcesAccessException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

// Facade - Provide a unified interface to a set of interfaces in a subsystem. Facade defines a higher-level interface that makes the subsystem easier to use.
public class NBPApiFacade {
    private volatile static NBPApiFacade instance = null;

    public static NBPApiFacade getInstance() {
        if (instance == null) {
            synchronized (NBPApiFacade.class) {
                if (instance == null) {
                    instance = new NBPApiFacade();
                }
            }
        }
        return instance;
    }

    private NBPApiFacade() {
    }

    public Rate getRate(String code) throws JsonParserException, OnlineResourcesAccessException {
        // http://api.nbp.pl/api/exchangerates/rates/a/{code}/
        String url = "http://api.nbp.pl/api/exchangerates/rates/a/%s/"; // code
        try {
            OnlineResourceFetcher fetcher = new OnlineResourceFetcher(String.format(url, code));
            if (fetcher.fetchResource()) {
                return Rate.deserialize(fetcher.getContent());
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
        try {
            OnlineResourceFetcher fetcher = new OnlineResourceFetcher(String.format(url, code, date));
            if (fetcher.fetchResource()) {
                return Rate.deserialize(fetcher.getContent());
            } else {
                throw new OnlineResourcesAccessException(fetcher.getResponseCode(), fetcher.getResponseMessage(), fetcher.getUrl());
            }
        } catch (IOException e) {
            throw new JsonParserException(e);
        }
    }

    public Rate getRates(String code, DatePeriod period) throws JsonParserException, OnlineResourcesAccessException {
        // http://api.nbp.pl/api/exchangerates/rates/a/{code}/{startDate}/{endDate}/
        String url = "http://api.nbp.pl/api/exchangerates/rates/a/%s/%s/%s/"; // code, startDate, endDate
        List<Rate> rateList = new LinkedList<>();
        List<DatePeriod> periods = period.split(367);
        try {
            String testUrl = "http://api.nbp.pl/api/exchangerates/rates/a/%s/"; // code, date
            OnlineResourceFetcher preFetcher = new OnlineResourceFetcher(String.format(testUrl, code));
            boolean fetchable = preFetcher.fetchResource();
            for (DatePeriod per : periods) {
                OnlineResourceFetcher fetcher = new OnlineResourceFetcher(String.format(url, code, per.getStartDate(), per.getEndDate()));
                if (fetcher.fetchResource()) {
                    rateList.add(Rate.deserialize(fetcher.getContent()));
                } else {
                    if (!fetchable)
                        throw new OnlineResourcesAccessException(fetcher.getResponseCode(), fetcher.getResponseMessage(), fetcher.getUrl());
                }
            }
        } catch (IOException e) {
            throw new JsonParserException(e);
        }
        Rate result = new Rate();
        Function<Function<Rate, String>, String> extract = f -> rateList
                .stream()
                .map(f)
                .distinct()
                .filter(Objects::nonNull)
                .findFirst()
                .orElse("n/a");
        result.setTable(extract.apply(Rate::getTable));
        result.setCountry(extract.apply(Rate::getCountry));
        result.setSymbol(extract.apply(Rate::getSymbol));
        result.setCurrency(extract.apply(Rate::getCurrency));
        result.setCode(extract.apply(Rate::getCode));
        result.setRates(rateList
                .stream()
                .map(Rate::getRates)
                .flatMap(Arrays::stream)
                .toArray(RRate[]::new));
        return result;
    }

    public Gold getGold() throws JsonParserException, OnlineResourcesAccessException {
        // http://api.nbp.pl/api/cenyzlota
        String url = "http://api.nbp.pl/api/cenyzlota";
        try {
            OnlineResourceFetcher fetcher = new OnlineResourceFetcher(url);
            if (fetcher.fetchResource()) {
                return Gold.deserializeOne(fetcher.getContent());
            } else {
                throw new OnlineResourcesAccessException(fetcher.getResponseCode(), fetcher.getResponseMessage(), fetcher.getUrl());
            }
        } catch (IOException e) {
            throw new JsonParserException(e);
        }
    }

    public Gold getGold(LocalDate date) throws JsonParserException, OnlineResourcesAccessException {
        // http://api.nbp.pl/api/cenyzlota
        String url = "http://api.nbp.pl/api/cenyzlota/%s/";
        try {
            OnlineResourceFetcher fetcher = new OnlineResourceFetcher(String.format(url, date));
            if (fetcher.fetchResource()) {
                return Gold.deserializeOne(fetcher.getContent());
            } else {
                throw new OnlineResourcesAccessException(fetcher.getResponseCode(), fetcher.getResponseMessage(), fetcher.getUrl());
            }
        } catch (IOException e) {
            throw new JsonParserException(e);
        }
    }

    public List<Gold> getGolds(DatePeriod period) throws OnlineResourcesAccessException, JsonParserException {
        // http://api.nbp.pl/api/cenyzlota/{startDate}/{endDate}
        String url = "http://api.nbp.pl/api/cenyzlota/%s/%s/"; // startDate, endDate
        List<Gold> result = new LinkedList<>();
        List<DatePeriod> periods = period.split(367);
        try {
            for (DatePeriod per : periods) {
                OnlineResourceFetcher fetcher = new OnlineResourceFetcher(String.format(url, per.getStartDate(), per.getEndDate()));
                if (fetcher.fetchResource()) {
                    result.addAll(Arrays.asList(Gold.deserializeMany(fetcher.getContent())));
                } else {
                    throw new OnlineResourcesAccessException(fetcher.getResponseCode(), fetcher.getResponseMessage(), fetcher.getUrl());
                }
            }
        } catch (IOException e) {
            throw new JsonParserException(e);
        }
        return result;
    }

    public Table getTable(char table) throws JsonParserException, OnlineResourcesAccessException {
        // http://api.nbp.pl/api/exchangerates/tables/{table}/{date}/
        String url = "http://api.nbp.pl/api/exchangerates/tables/%c/"; // table, date
        try {
            OnlineResourceFetcher fetcher = new OnlineResourceFetcher(String.format(url, table));
            if (fetcher.fetchResource()) {
                return Table.deserializeOne(fetcher.getContent());
            } else {
                throw new OnlineResourcesAccessException(fetcher.getResponseCode(), fetcher.getResponseMessage(), fetcher.getUrl());
            }
        } catch (IOException e) {
            throw new JsonParserException(e);
        }
    }

    public Table getTable(char table, LocalDate date) throws JsonParserException, OnlineResourcesAccessException {
        // http://api.nbp.pl/api/exchangerates/tables/{table}/{date}/
        String url = "http://api.nbp.pl/api/exchangerates/tables/%c/%s/"; // table, date
        try {
            OnlineResourceFetcher fetcher = new OnlineResourceFetcher(String.format(url, table, date));
            if (fetcher.fetchResource()) {
                return Table.deserializeOne(fetcher.getContent());
            } else {
                throw new OnlineResourcesAccessException(fetcher.getResponseCode(), fetcher.getResponseMessage(), fetcher.getUrl());
            }
        } catch (IOException e) {
            throw new JsonParserException(e);
        }
    }

    public List<Table> getTables(char table, DatePeriod period) throws OnlineResourcesAccessException, JsonParserException {
        // http://api.nbp.pl/api/exchangerates/tables/{table}/{startDate}/{endDate}/
        String url = "http://api.nbp.pl/api/exchangerates/tables/%c/%s/%s/"; // table, startDate, endDate
        List<Table> result = new LinkedList<>();
        List<DatePeriod> periods = period.split(93);
        try {
            for (DatePeriod per : periods) {
                OnlineResourceFetcher fetcher = new OnlineResourceFetcher(String.format(url, table, per.getStartDate(), per.getEndDate()));
                if (fetcher.fetchResource()) {
                    result.addAll(Arrays.asList(Table.deserializeMany(fetcher.getContent())));
                } else {
                    throw new OnlineResourcesAccessException(fetcher.getResponseCode(), fetcher.getResponseMessage(), fetcher.getUrl());
                }
            }
        } catch (IOException e) {
            throw new JsonParserException(e);
        }
        return result;
    }
}
