package com.oocl.todolist.integration;

import com.oocl.todolist.model.Tag;
import com.oocl.todolist.model.Todo;
import com.oocl.todolist.repository.TagRepository;
import com.oocl.todolist.repository.TodoRepository;
import com.oocl.todolist.service.TodoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoIntegrationTest {
    @Autowired
    TodoRepository todoRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    MockMvc mockMvc;

    @AfterEach
    void tearDown() {
        todoRepository.deleteAll();
        tagRepository.deleteAll();
    }

    @Test
    void should_return_all_todos_when_called_get_all_given_todos() throws Exception {
        //given
        Tag tag1 = new Tag("content1", "#123456");
        Tag tag2 = new Tag("content2", "#000000");
        List<Tag> tags = Arrays.asList(tag1, tag2);
        tagRepository.insert(tags);
        Todo todo1 = new Todo("message 1", false, tags);
        Todo todo2 = new Todo("message 2", true);
        todoRepository.insert(todo1);
        todoRepository.insert(todo2);

        //when
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].message").value("message 1"))
                .andExpect(jsonPath("$[0].status").value(false))
                .andExpect(jsonPath("$[0].tags[0].content").value("content1"))
                .andExpect(jsonPath("$[0].tags[0].color").value("#123456"))
                .andExpect(jsonPath("$[0].tags[1].content").value("content2"))
                .andExpect(jsonPath("$[0].tags[1].color").value("#000000"))
                .andExpect(jsonPath("$[1].id").isString())
                .andExpect(jsonPath("$[1].message").value("message 2"))
                .andExpect(jsonPath("$[1].status").value(true))
                .andExpect(jsonPath("$[1].tags").isEmpty());

    }

    @Test
    void should_return_created_todo_when_called_create_given_todo_request() throws Exception {
        //given
        String createTodoJson = "{\n" +
                "    \"message\": \"message 1\",\n" +
                "    \"status\": false,\n" +
                "    \"tags\": []\n" +
                "}";

        //when
        mockMvc.perform(
                post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createTodoJson)
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.message").value("message 1"))
                .andExpect(jsonPath("$.status").value(false))
                .andExpect(jsonPath("$.tags").isEmpty());
    }

    @Test
    void should_return_update_todo_when_called_update_given_todo_id_and_update_todo_request() throws Exception {
        //given
        Todo todo = new Todo("message old", true);
        todoRepository.insert(todo);
        String updateTodoJson = "{\n" +
                "    \"message\": \"message 1\",\n" +
                "    \"status\": false,\n" +
                "    \"tags\": []\n" +
                "}";

        //when
        mockMvc.perform(
                put("/todos/" + todo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateTodoJson)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(todo.getId()))
                .andExpect(jsonPath("$.message").value("message 1"))
                .andExpect(jsonPath("$.status").value(false))
                .andExpect(jsonPath("$.tags").isEmpty());
    }
}
