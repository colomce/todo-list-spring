package com.oocl.todoapp.controllers;

import com.oocl.todoapp.dto.TodoRequest;
import com.oocl.todoapp.dto.TodoResponse;
import com.oocl.todoapp.mapper.TodoMapper;
import com.oocl.todoapp.models.Todo;
import com.oocl.todoapp.services.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private TodoResponse create(@RequestBody TodoRequest todoRequest){
        Todo todo = todoMapper.toEntity(todoRequest);
        Todo createdTodo = todoService.create(todo);
        return todoMapper.toResponse(createdTodo);
    }

    @GetMapping(params = {"done"})
    private List<TodoResponse> getTodoByDone(@RequestParam("done") Boolean done){
        return todoMapper.toResponseList(todoService.getTodoByDone(done));
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable("id") Integer id){
        todoService.delete(id);
    }

    @PutMapping("/{id}")
    private TodoResponse update(@PathVariable("id") Integer id, @RequestBody TodoRequest todoRequest){
        Todo todo = todoMapper.toEntity(todoRequest);
        Todo updatedTodo = todoService.update(id, todo);
        return todoMapper.toResponse(updatedTodo);
    }
}
