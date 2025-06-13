package com.example.BookMyShowBackend.exceptions;

public class ShowNotFoundException extends Exception {

    public ShowNotFoundException(String message) {
        super(message);
    }

    public ShowNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
