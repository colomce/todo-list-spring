package com.oocl.todoapp.controllers;

import com.oocl.todoapp.dto.TodoResponse;
import com.oocl.todoapp.mapper.TodoMapper;
import com.oocl.todoapp.services.TodoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;
    private final TodoMapper todoMapper;

    public TodoController(TodoService todoService, TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }

    @GetMapping
    private List<TodoResponse> getAll() {
        return todoMapper.toResponseList(todoService.getAll());
    }


}
