package com.ctrlcutter.backend.persistence.service;


public class APIRequestException extends RuntimeException {

    public APIRequestException(String exceptionString) {
        super(exceptionString);
    }

    public APIRequestException(String exceptionString, Throwable exceptionThrowable) {
        super(exceptionString, exceptionThrowable);
    }
}
