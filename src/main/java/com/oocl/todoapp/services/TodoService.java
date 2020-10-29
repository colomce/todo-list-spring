package com.oocl.todoapp.services;

import com.oocl.todoapp.exceptions.TodoNotFoundException;
import com.oocl.todoapp.models.Todo;
import com.oocl.todoapp.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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

    private Todo searchById(Integer id) {
        return todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException("Todo with id:" + id + " not found"));
    }
    public void delete(Integer id) {
        Todo todo = searchById(id);
        todoRepository.delete(todo);
    }

    public Todo update(Integer id, Todo updateTodo) {
        Todo todo = searchById(id);
        todo.setText(updateTodo.getText());
        todo.setDone(updateTodo.getDone());
        return todoRepository.save(todo);
    }

    public List<Todo> getTodoByDone(Boolean isDone) {
        return todoRepository.findByDone(isDone);
    }
}
