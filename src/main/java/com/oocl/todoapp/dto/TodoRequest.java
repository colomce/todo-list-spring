package com.oocl.todoapp.dto;

public class TodoRequest {
    private String text;
    private Boolean done;

    public TodoRequest(String text, Boolean done) {
        this.text = text;
        this.done = done;
    }

    public String getText() {
        return text;
    }

    public Boolean getDone() {
        return done;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
