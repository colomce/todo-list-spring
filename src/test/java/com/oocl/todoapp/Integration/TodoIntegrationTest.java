package com.oocl.todoapp.Integration;

import com.oocl.todoapp.models.Todo;
import com.oocl.todoapp.repository.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TodoIntegrationTest {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    void tearDown() {
        todoRepository.deleteAll();
    }

    @Test
    void should_return_all_todos_when_getAll_given_1_todo() throws Exception {
        //given
        Todo todo = new Todo(1, "Code", false);
        todoRepository.save(todo);

        //when then
        mockMvc.perform(get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].text").value("Code"))
                .andExpect(jsonPath("$[0].done").value(false));
    }

    @Test
    void should_create_todo_when_create_given_todo() throws Exception {
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
    void should_return_all_undone_todos_when_getAll_given_1_undone_todos() throws Exception {
        //given
        Todo undoneTodo = new Todo(1, "Code", false);
        Todo doneTodo = new Todo(2, "Refactor", true);
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
    void should_delete_todo_when_delete() throws Exception {
        //given
        Todo todo = new Todo(1, "Code", false);
        Todo createdTodo = todoRepository.save(todo);

        //when then
        mockMvc.perform(delete("/api/todos/{id}", createdTodo.getId()))
                .andExpect(status().isOk());

        Optional<Todo> fetchDeletedTodo = todoRepository.findById(createdTodo.getId());
        assertFalse(fetchDeletedTodo.isPresent());
    }

    @Test
    void should_return_updated_todo_when_update_given_todo() throws Exception {
        //given
        Todo todo = new Todo(1, "Code", false);
        Todo createdTodo = todoRepository.save(todo);

        String updatedJson = "{\n" +
                "    \"text\" : \"Refactor\",\n" +
                "    \"done\" : true\n" +
                "}";

        //when then
        mockMvc.perform(put("/api/todos/{id}", createdTodo.getId())
                .content(updatedJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.text").value("Refactor"))
                .andExpect(jsonPath("$.done").value(true));
    }

    @Test
    void should_return_the_error_response_with_message_and_status_when_delete_by_id_given_invalid_todo_id() throws Exception {
        //given
        Integer todoId = 12345;

        // when then
        mockMvc.perform(delete("/api/todos/{todoId}", todoId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Todo with id:12345 not found"))
                .andExpect(jsonPath("$.status").value("NOT_FOUND"))
                .andReturn();
    }

    @Test
    void should_return_the_error_response_with_message_and_status_when_update_id_given_invalid_todoId() throws Exception {
        //given
        Integer todoId = 12345;
        String todoUpdateJson = "{\"text\" : \"Refactoring\"}";

        // when then
        mockMvc.perform(put("/api/todos/{todoId}", todoId)
                .content(todoUpdateJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Todo with id:12345 not found"))
                .andExpect(jsonPath("$.status").value("NOT_FOUND"))
                .andReturn();
    }

    @Test
    void should_return_the_error_response_with_message_and_status_when_create_given_empty_todo_text() throws Exception {
        //given
        String todoUpdateJson = "{\"text\" : \"\"}";

        // when then
        mockMvc.perform(post("/api/todos")
                .content(todoUpdateJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Todo has invalid fields"))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andReturn();
    }

    @Test
    void should_return_the_error_response_with_message_and_status_when_update_given_empty_todo_text() throws Exception {
        //given
        Todo todo = new Todo(1, "Code", false);
        Todo createdTodo = todoRepository.save(todo);
        String todoUpdateJson = "{\"text\" : \"\"}";

        // when then
        mockMvc.perform(put("/api/todos/{todoId}", createdTodo.getId())
                .content(todoUpdateJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Todo has invalid fields"))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andReturn();
    }

}
