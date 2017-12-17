package com.yarmarq.serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;


public class Gold implements Serializable {
    @JsonProperty("data")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;                 // – data publikacji
    @JsonProperty("cena")
    private Double price;                   // – wyliczona w NBP price 1 g złota (w próbie 1000)

    @JsonProperty("data")
    public LocalDate getDate() {
        return date;
    }

    @JsonProperty("data")
    public void setDate(LocalDate date) {
        this.date = date;
    }

    @JsonProperty("cena")
    public Double getPrice() {
        return price;
    }

    @JsonProperty("cena")
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("> G: [Date: %s] [Price: %.2f]", date, price);
    }

    public static void main(String[] args) {
        Gold geld = new Gold();
        geld.setPrice(1234.567);
        LocalDate date = LocalDate.parse("2017-12-15");
        geld.setDate(date);
        System.out.println(geld);

        ObjectMapper mapper = new ObjectMapper();
        try {
            Gold[] golds = mapper.readValue(new URL("http://api.nbp.pl/api/cenyzlota/last/30/?format=json"), Gold[].class);
            Arrays.asList(golds).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
