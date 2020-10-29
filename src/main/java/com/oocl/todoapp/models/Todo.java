package com.oocl.todoapp.models;

public class Todo {
    private Integer id;
    private String text;
    private Boolean done;

    public Todo(Integer id, String text, Boolean done) {
        this.id = id;
        this.text = text;
        this.done = done;
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
}
