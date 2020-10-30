package com.oocl.todoapp.unit;

import com.oocl.todoapp.models.Todo;
import com.oocl.todoapp.repository.TodoRepository;
import com.oocl.todoapp.services.TodoService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

public class TodoServiceTest {

    @Test
    void should_return_2_todo_when_get_all_given_2_todos() {
        //given
        Todo firstTodo = new Todo(1, "", true);
        Todo secondTodo = new Todo(2, "", true);
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
        Todo newTodo = new Todo(1, "Code", true);
        TodoRepository todoRepository = mock(TodoRepository.class);
        TodoService todoService = new TodoService(todoRepository);
        when(todoRepository.save(newTodo)).thenReturn(newTodo);

        //when
        Todo todo = todoService.create(newTodo);


        //then
        assertEquals(1, todo.getId());
    }

    @Test
    public void should_create_todo_with_text_Charlie_when_create_given_todo_with_id_1_text_Charlie() {
        //given
        Todo newTodo = new Todo(1, "Charlie", true);
        TodoRepository todoRepository = mock(TodoRepository.class);
        TodoService todoService = new TodoService(todoRepository);
        when(todoRepository.save(newTodo)).thenReturn(newTodo);

        //when
        Todo todo = todoService.create(newTodo);


        //then
        assertEquals(1, todo.getId());
        assertEquals("Charlie", todo.getText());
    }

    @Test
    public void should_create_todo_with_done_true_when_create_given_todo_with_todo_done_true() {
        //given
        Todo newTodo = new Todo(1, "Charlie", true);
        TodoRepository todoRepository = mock(TodoRepository.class);
        TodoService todoService = new TodoService(todoRepository);
        when(todoRepository.save(newTodo)).thenReturn(newTodo);

        //when
        Todo todo = todoService.create(newTodo);


        //then
        assertEquals(1, todo.getId());
        assertEquals("Charlie", todo.getText());
        assertTrue(todo.getDone());
    }

    @Test
    public void should_trigger_repository_once_when_service_delete_called_given_todo() {
        //given
        Todo todo = new Todo(1, "Charlie", true);
        TodoRepository todoRepository = mock(TodoRepository.class);
        when(todoRepository.findById(todo.getId())).thenReturn(Optional.of(todo));
        TodoService todoService = new TodoService(todoRepository);

        //when
        todoService.delete(todo.getId());

        //then
        verify(todoRepository, times(1)).delete(todo);
    }

    @Test
    public void should_return_updated_todo_done_true_when_update_given_todo_with_done_of_false() {
        //given
        Todo todo = new Todo(1, "Charlie", false);
        Todo updateTodo = new Todo(1, "Charlie", true);
        Optional<Todo> optionalTodo = Optional.of(updateTodo);
        TodoRepository todoRepository = mock(TodoRepository.class);
        when(todoRepository.findById(todo.getId())).thenReturn(optionalTodo);
        when(todoRepository.save(optionalTodo.get())).thenReturn(updateTodo);
        TodoService todoService = new TodoService(todoRepository);

        //when
        Todo updatedTodo = todoService.update(todo.getId(), updateTodo);

        //then
        assertSame(updateTodo, updatedTodo);
    }

    @Test
    public void should_return_2_done_todo_when_get_all_given_2_done_todos() {
        //given
        Todo firstTodo = new Todo(1, "", true);
        Todo secondTodo = new Todo(2, "", true);
        TodoRepository todoRepository = mock(TodoRepository.class);
        when(todoRepository.findByDone(true)).thenReturn(Arrays.asList(firstTodo, secondTodo));
        TodoService todoService = new TodoService(todoRepository);

        //when
        Integer todoCount = todoService.getTodoByDone(true).size();

        //then
        assertEquals(2, todoCount);
    }

    @Test
    public void should_return_2_undone_todo_when_get_all_given_2_undone_todos() {
        //given
        Todo firstTodo = new Todo(1, "", false);
        Todo secondTodo = new Todo(2, "", false);
        TodoRepository todoRepository = mock(TodoRepository.class);
        when(todoRepository.findByDone(false)).thenReturn(Arrays.asList(firstTodo, secondTodo));
        TodoService todoService = new TodoService(todoRepository);

        //when
        Integer todoCount = todoService.getTodoByDone(false).size();

        //then
        assertEquals(2, todoCount);
    }
}
