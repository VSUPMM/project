package com.example.demo.exception;

public class ResultNotFoundException extends RuntimeException{
    public ResultNotFoundException(Long userId,Long testId) {
        super("Could not find result for user "
                + userId + " in test " + testId );
    }
}
