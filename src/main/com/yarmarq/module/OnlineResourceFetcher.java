package com.yarmarq.module;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.stream.Collectors;

public class OnlineResourceFetcher {
    private int responseCode = -1;
    private String responseMessage;
    private URL url;
    private String content;

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public URL getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return String.format("Response code: %d\nResponse message: %s\nURL: %s\nContent: %s", responseCode, responseMessage, url, content);
    }

    public OnlineResourceFetcher(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public boolean fetchResource() {
        InputStream is;
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            responseCode = con.getResponseCode();
            responseMessage = con.getResponseMessage();
            is = con.getInputStream();
        } catch (IOException e) {
            return responseCode == 200;
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            content = br.lines().collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseCode == 200;
    }
}
