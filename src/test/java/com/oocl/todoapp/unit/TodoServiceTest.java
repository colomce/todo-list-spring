package com.oocl.todoapp.unit;

import com.oocl.todoapp.models.Todo;
import com.oocl.todoapp.repository.TodoRepository;
import com.oocl.todoapp.services.TodoService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TodoServiceTest {

    @Test
    void should_return_2_todo_when_get_all_given_2_todos() {
        //given
        Todo firstTodo = new Todo();
        Todo secondTodo = new Todo();
        TodoRepository todoRepository = mock(TodoRepository.class);
        when(todoRepository.findAll()).thenReturn(Arrays.asList(firstTodo, secondTodo));
        TodoService todoService = new TodoService(todoRepository);

        //when
        Integer todoCount = todoService.getAll().size();

        //then
        assertEquals(2, todoCount);
    }
}
