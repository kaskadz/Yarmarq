package com.yarmarq.exception;

import java.net.URL;

public class OnlineResourcesAccessException extends Exception {
    private int responseCode;
    private String responseMessage;
    private URL url;

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public URL getUrl() {
        return url;
    }

    @Override
    public String getMessage() {
        if (responseCode == -1) return String.format("Couldn't access URL: %s", url);
        return String.format("Server returned HTTP response code: %d\nWith message: %s\nFor URL: %s", responseCode, responseMessage, url);
    }

    public OnlineResourcesAccessException(int responseCode, String responseMessage, URL url) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.url = url;
    }
}
