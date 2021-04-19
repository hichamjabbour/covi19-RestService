package com.data.covid19;

public class SummaryNotFoundException extends RuntimeException {

    public SummaryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}