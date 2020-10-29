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
        Todo firstTodo = new Todo(1);
        Todo secondTodo = new Todo(2);
        TodoRepository todoRepository = mock(TodoRepository.class);
        when(todoRepository.findAll()).thenReturn(Arrays.asList(firstTodo, secondTodo));
        TodoService todoService = new TodoService(todoRepository);

        //when
        Integer todoCount = todoService.getAll().size();

        //then
        assertEquals(2, todoCount);
    }

    @Test
    public void should_create_todo_with_id_i_when_create_given_todo_with_id_1() {
        //given
        Todo newTodo = new Todo(1);
        TodoRepository todoRepository = mock(TodoRepository.class);
        TodoService todoService = new TodoService(todoRepository);
        when(todoRepository.save(newTodo)).thenReturn(newTodo);

        //when
        Todo todo = todoService.create(newTodo);


        //then
        assertEquals(1, todo.getId());

    }
}
