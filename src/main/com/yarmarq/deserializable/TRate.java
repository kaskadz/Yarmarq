package com.yarmarq.deserializable;

import java.io.Serializable;

public class TRate implements Serializable {
    private String country;                 // - nazwa kraju
    private String currency;                // – nazwa waluty
    private String symbol;
    private String code;                    // – kod waluty
    private Double bid;                     // – przeliczony kurs kupna waluty (dotyczy tabeli C)
    private Double ask;                     // – przeliczony kurs sprzedaży waluty (dotyczy tabeli C)
    private Double mid;                     // – przeliczony kurs średni waluty (dotyczy tabel A oraz B)

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

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public Double getMid() {
        return mid;
    }

    public void setMid(Double mid) {
        this.mid = mid;
    }

    @Override
    public String toString() {
        return String.format("Code: %3s, Currency: %-30s, Country: %-30s, mid: %f, bid: %f, ask: %f", code, currency, country, mid, bid, ask);
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
}
