package com.oocl.todolist.dto;

public class TagResponse {
    private String id;
    private String content;
    private String color;

    public TagResponse() {
    }

    public TagResponse(String id, String content, String color) {
        this.id = id;
        this.content = content;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
