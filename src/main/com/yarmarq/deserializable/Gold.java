package com.yarmarq.deserializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Gold implements Serializable {
    @JsonProperty("data")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;                 // – data publikacji
    @JsonProperty("cena")
    private Double price;                   // – wyliczona w NBP price 1 g złota (w próbie 1000)

    public LocalDate getDate() {
        return date;
    }

    @JsonSetter("data")
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    @JsonSetter("cena")
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format(
                "> GOLD: [Date]: %s [Price]: %.2f",
                date, price
        );
    }

    public static Gold deserializeOne(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Gold[] golds = mapper.readValue(json, Gold[].class);
        return golds[0];
    }

    public static Gold[] deserializeMany(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Gold[].class);
    }
}
