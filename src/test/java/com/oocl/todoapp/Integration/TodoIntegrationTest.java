package com.oocl.todoapp.Integration;

import com.oocl.todoapp.models.Todo;
import com.oocl.todoapp.repository.TodoRepository;
import com.oocl.todoapp.services.TodoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoIntegrationTest {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    void tearDown(){
        todoRepository.deleteAll();
    }

    @Test
    public void should_return_all_todos_when_getAll_given_1_todo() throws Exception {
        //given
        Todo todo = new Todo(1,"Code", false);
        todoRepository.save(todo);

        //when then
        mockMvc.perform(get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].text").value("Code"))
                .andExpect(jsonPath("$[0].done").value(false));
    }

    @Test
    public void should_create_todo_when_create_given_todo() throws Exception {
        //given
        String todoJson = "{\n" +
                "    \"text\" : \"Code\",\n" +
                "    \"done\" : false\n" +
                "}";

        //when then
        mockMvc.perform(post("/api/todos")
                .content(todoJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.text").value("Code"))
                .andExpect(jsonPath("$.done").value(false));
    }

    @Test
    public void should_return_all_undone_todos_when_getAll_given_1_undone_todos() throws Exception {
        //given
        Todo undoneTodo = new Todo(1,"Code", false);
        Todo doneTodo = new Todo(2,"Refactor", true);
        todoRepository.save(undoneTodo);
        todoRepository.save(doneTodo);

        //when then
        mockMvc.perform(get("/api/todos?done=false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].text").value("Code"))
                .andExpect(jsonPath("$[0].done").value(false));
    }

    @Test
    public void should_delete_todo_when_delete() throws Exception {
        //given
        Todo todo = new Todo(1,"Code", false);
        Todo createdTodo = todoRepository.save(todo);

        //when then
        mockMvc.perform(delete("/api/todos/{id}", createdTodo.getId()))
                .andExpect(status().isOk());

        Optional<Todo> fetchDeletedTodo = todoRepository.findById(createdTodo.getId());
        assertFalse(fetchDeletedTodo.isPresent());
    }

}
