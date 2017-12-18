package com.yarmarq.module;

import com.yarmarq.exception.OnlineResourcesAccessException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

class OnlineResourceFetcherTest {

    @Test
    void getContent() {

//        try {
//            OnlineResourceFetcher fetcher = new OnlineResourceFetcher("http://api.nbp.pl/api/cenyzlota/2013-01-02/?format=json");
//            assertEquals(fetcher.getContent(), "[{\"data\":\"2013-01-02\",\"cena\":165.83}]");
//        } catch (IOException e) {
//            fail("Error occurred, when accessing online content.");
//        } catch (OnlineResourcesAccessException e) {
//            System.out.println("Ups..");
//            System.out.println(e.getResponseCode());
//            System.out.println(e.getResponseMessage());
//            System.out.println(e.getUrl());
//            fail("Fetch error. :(");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
    }
}