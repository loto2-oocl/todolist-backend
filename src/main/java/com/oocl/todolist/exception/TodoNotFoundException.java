package com.oocl.todolist.exception;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(String id) {
        super(String.format("Todo with id:%s cannot be found", id));
    }
}
