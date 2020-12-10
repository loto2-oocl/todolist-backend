package com.oocl.todolist.dto;

import com.oocl.todolist.model.Tag;

import java.util.List;

public class TodoRequest {
    private String message;
    private boolean status;
    private List<Tag> tags;

    public TodoRequest() {
    }

    public TodoRequest(String message, boolean status, List<Tag> tags) {
        this.message = message;
        this.status = status;
        this.tags = tags;
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
