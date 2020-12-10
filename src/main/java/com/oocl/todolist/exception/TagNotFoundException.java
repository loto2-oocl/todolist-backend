package com.oocl.todolist.exception;

public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException(String id) {
        super(String.format("Tag with id:%s cannot be found", id));
    }
}
