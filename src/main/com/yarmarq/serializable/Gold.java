package com.yarmarq.serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yarmarq.module.DateFormatter;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


public class Gold implements Serializable {
    @JsonProperty("data")
    private Date date;                      // – date publikacji
    @JsonProperty("cena")
    private Double price;                   // – wyliczona w NBP price 1 g złota (w próbie 1000)

    @JsonProperty("data")
    public Date getDate() {
        return date;
    }

    @JsonProperty("data")
    public void setDate(Date date) {
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

    public String getFormattedDate() {
        return DateFormatter.formatDate(date);
    }

    @Override
    public String toString() {
        String date = getFormattedDate();
        return String.format("G:[%.2f] %s", price, date);
    }

    public static void main(String[] args) throws ParseException {
        Gold geld = new Gold();
        geld.setPrice(1234.567);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("1997-08-10");
        geld.setDate(date);
        System.out.println(geld);

        ObjectMapper mapper = new ObjectMapper();
        try {
//            GoldSubComm[] golds = mapper.readValue("[{\"data\":\"2017-12-12\",\"cena\":142.89}]", GoldSubComm[].class);
            Gold[] golds = mapper.readValue(new URL("http://api.nbp.pl/api/cenyzlota/last/30/?format=json"), Gold[].class);
            Arrays.asList(golds).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
