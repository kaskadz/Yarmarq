package com.yarmarq.deserializable;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

public class Rate implements Serializable {
    private String table;                   // typ tabeli
    private String country;                 // nazwa kraju
    private String symbol;                  // symbol waluty (numeryczny, dotyczy kursów archiwalnych)
    private String currency;                // nazwa waluty
    private String code;                    // kod waluty
    private RRate[] rates;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public RRate[] getRates() {
        return rates;
    }

    public void setRates(RRate[] rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("> RATE: [table: ");
        sb.append(table);
        sb.append("] [country: ");
        sb.append(country);
        sb.append("] [symbol: ");
        sb.append(symbol);
        sb.append("] [currency: ");
        sb.append(currency);
        sb.append("] [code: ");
        sb.append(code);
        sb.append("]");
        sb.append("\n");
        for (RRate rate : rates) {
            sb.append(rate);
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Rate rate = mapper.readValue(new URL("http://api.nbp.pl/api/exchangerates/rates/a/gbp/2012-01-01/2012-01-31/?format=json"), Rate.class);
            System.out.println(rate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
