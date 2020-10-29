package com.oocl.todoapp.services;

import com.oocl.todoapp.models.Todo;
import com.oocl.todoapp.repository.TodoRepository;

import java.util.List;

public class TodoService {
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAll() {
        return todoRepository.findAll();
    }
}