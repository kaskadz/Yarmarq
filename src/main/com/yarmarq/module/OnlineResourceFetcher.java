package com.yarmarq.module;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

/**
 * Class, that is responsible for getting online data by GET method.
 */
public class OnlineResourceFetcher {
    /**
     * Response code of the request, that was made. -1 stands for request failed due to broken internet connection or
     * request that hasn't been sent yet.
     */
    private int responseCode = -1;
    /**
     * Response message - it contains a message, that is sent with the error code, usually describing the error.
     */
    private String responseMessage;
    /**
     * An url, from which to fetch the data.
     */
    private URL url;
    /**
     * String, containing data, downloaded from url.
     */
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

    /**
     * @return Fetched content as a string.
     */
    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return String.format("Response code: %d\nResponse message: %s\nURL: %s\nContent: %s", responseCode, responseMessage, url, content);
    }

    /**
     * A constructor, that takes url and initializes url field, checking for properly formed url.
     *
     * @param url url address, from which to download data
     * @throws MalformedURLException thrown, when url is not properly formatted
     */
    public OnlineResourceFetcher(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    /**
     * Method, that invokes fetching online data and returns information about success or failure of a process.
     *
     * @return true is fetching succeeds, otherwise false
     */
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
