package com.example.BookMyShowBackend.exceptions;

public class ShowSeatNotFoundException extends Exception {

    public ShowSeatNotFoundException(String message) {
        super(message);
    }

    public ShowSeatNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
