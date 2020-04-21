package com.example.demo.excpection;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(Integer id) {
        super("Could not find task " + id);
    }
}
