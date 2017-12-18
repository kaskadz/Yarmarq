package com.yarmarq.exception;

import java.io.IOException;

public class JsonParserException extends Exception {
    public JsonParserException(IOException e) {
        super(e);
    }
}
