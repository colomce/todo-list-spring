package com.oocl.todoapp.models;

public class Todo {
    private Integer id;
    private String text;

    public Todo(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
