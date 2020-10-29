package com.oocl.todoapp.dto;

public class TodoResponse {
    private Integer id;
    private String text;
    private Boolean done;

    public TodoResponse(Integer id, String text, Boolean done) {
        this.id = id;
        this.text = text;
        this.done = done;
    }

    public TodoResponse() {

    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Boolean getDone() {
        return done;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
