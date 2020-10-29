package com.oocl.todoapp.services;

import com.oocl.todoapp.models.Todo;
import com.oocl.todoapp.repository.TodoRepository;

import java.util.List;
import java.util.Optional;

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

    public Todo update(Integer id, Todo updateTodo) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            optionalTodo.get().setText(updateTodo.getText());
            optionalTodo.get().setDone(updateTodo.getDone());
            return todoRepository.save(optionalTodo.get());
        }
        return null;
    }
}
