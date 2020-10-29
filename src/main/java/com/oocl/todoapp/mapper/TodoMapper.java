package com.oocl.todoapp.mapper;

import com.oocl.todoapp.dto.TodoResponse;
import com.oocl.todoapp.models.Todo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoMapper {
    public List<TodoResponse> toResponseList(List<Todo> todoList) {
        return todoList.stream()
                .map(todo -> new TodoResponse(todo.getId(), todo.getText(), todo.getDone()))
                .collect(Collectors.toList());
    }

}
