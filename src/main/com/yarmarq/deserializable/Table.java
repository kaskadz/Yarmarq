package com.yarmarq.deserializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
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

    public int getRatesCount() {
        return rates.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(
                "> TABLE: [Table]: %s [No]: %s [Trading date]: %s [Effective date]: %s [Rates]: %d\n",
                table, no, tradingDate, effectiveDate, getRatesCount()
        ));
        for (TRate rate : rates) {
            sb.append(rate);
            sb.append("\n");
        }
        return sb.toString();
    }

    public static Table deserializeOne(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Table[] tables = mapper.readValue(json, Table[].class);
        return tables[0];
    }

    public static Table[] deserializeMany(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Table[].class);
    }
}
