package com.yarmarq.module;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class OnlineResourceFetcherTest {

    @Test
    void getContent() {
        try {
            assertEquals(OnlineResourceFetcher.getContent("http://api.nbp.pl/api/cenyzlota/2013-01-02/?format=json"), "[{\"data\":\"2013-01-02\",\"cena\":165.83}]");
        } catch (IOException e) {
            fail("Error occurred, when accessing online content.");
        }
    }
}