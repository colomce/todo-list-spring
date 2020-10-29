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

    public Todo create(Todo newTodo) {
        return todoRepository.save(newTodo);
    }

    public void delete(Todo todo) {
        todoRepository.delete(todo);
    }
}
