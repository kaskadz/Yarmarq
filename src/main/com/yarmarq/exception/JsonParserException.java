package com.yarmarq.exception;

import java.io.IOException;

public class JsonParserException extends Exception {
    public JsonParserException() {
        super("Error, while parsing input data.");
    }
}
