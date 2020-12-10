package com.oocl.todolist.dto;

import com.oocl.todolist.model.Tag;

import java.util.List;

public class TodoResponse {
    private String id;
    private String message;
    private boolean status;
    private List<Tag> tags;

    public TodoResponse() {
    }

    public TodoResponse(String id, String message, boolean status, List<Tag> tags) {
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
