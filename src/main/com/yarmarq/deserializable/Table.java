package com.yarmarq.deserializable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;

public class Table implements Serializable {
    private String table;                   // typ tabeli
    private String no;                      // numer tabeli
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate tradingDate;               // data notowania (dotyczy tabeli C)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate effectiveDate;             // data publikacji
    private TRate[] rates;                  // lista kursów poszczególnych walut w tabeli

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public LocalDate getTradingDate() {
        return tradingDate;
    }

    public void setTradingDate(LocalDate tradingDate) {
        this.tradingDate = tradingDate;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public TRate[] getRates() {
        return rates;
    }

    public void setRates(TRate[] rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("> TABLE: [Table: ");
        sb.append(table);
        sb.append("] [No: ");
        sb.append(no);
        sb.append("] [Trading Date: ");
        sb.append(tradingDate);
        sb.append("] [Effective Date: ");
        sb.append(effectiveDate);
        sb.append("]\n");
        for (TRate rate : rates) {
            sb.append(rate);
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Table[] table = mapper.readValue(new URL("http://api.nbp.pl/api/exchangerates/tables/b/2012-01-01/2012-01-31/?format=json"), Table[].class);
            Arrays.asList(table).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
