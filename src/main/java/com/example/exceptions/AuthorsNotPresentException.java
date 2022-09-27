package com.example.exceptions;

public class AuthorsNotPresentException  extends Exception {

    public AuthorsNotPresentException(String msg) {
        super(msg);

    }
}