package com.yarmarq.deserializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

@JsonIgnoreProperties(ignoreUnknown = true)
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
        return (country == null) ? "n/a" : country;
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
        sb.append(String.format(
                "> RATE: [Table]: %s [Country]: %s [Symbol]: %s [Currency]: %s [Code]: %s\n",
                table, country, symbol, currency, code
        ));
        for (RRate rate : rates) {
            sb.append(rate);
            sb.append("\n");
        }
        return sb.toString();
    }

    public static Rate deserialize(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Rate.class);
    }
}
