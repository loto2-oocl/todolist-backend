package com.oocl.todolist.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Collections;
import java.util.List;

@Document
public class Todo {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String message;
    private boolean status;
    @DBRef(lazy = true)
    private List<Tag> tags;

    public Todo() {
    }

    public Todo(String message, boolean status) {
        this.message = message;
        this.status = status;
        this.tags = Collections.emptyList();
    }

    public Todo(String message, boolean status, List<Tag> tags) {
        this.message = message;
        this.status = status;
        this.tags = tags;
    }

    public Todo(String id, String message, boolean status, List<Tag> tags) {
        this.id = id;
        this.message = message;
        this.status = status;
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
