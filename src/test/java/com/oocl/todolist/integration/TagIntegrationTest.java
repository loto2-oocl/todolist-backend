package com.oocl.todolist.integration;

import com.oocl.todolist.model.Tag;
import com.oocl.todolist.repository.TagRepository;
import com.oocl.todolist.service.TagService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TagIntegrationTest {
    @Autowired
    TagService tagService;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    MockMvc mockMvc;

    @AfterEach
    void tearDown() {
        this.tagRepository.deleteAll();
    }

    @Test
    void should_return_all_tags_when_called_get_all_given_tags() throws Exception {
        //given
        Tag tag = new Tag("content1", "#123456");
        this.tagRepository.insert(tag);

        //when
        mockMvc.perform(get("/tags"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].content").value("content1"))
                .andExpect(jsonPath("$[0].color").value("#123456"));

    }

    @Test
    void should_return_created_tag_when_called_create_given_tag() throws Exception {
        //given
        String tagJson = "{\n" +
                "    \"content\": \"content\",\n" +
                "    \"color\": \"#123456\"\n" +
                "}";

        //when
        mockMvc.perform(
                post("/tags")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tagJson)
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.content").value("content"))
                .andExpect(jsonPath("$.color").value("#123456"));
    }

    @Test
    void should_return_updated_tag_when_called_update_given_tag_id_and_tag_update() throws Exception {
        //given
        Tag tag = new Tag("old content", "#000000");
        this.tagRepository.insert(tag);
        String updateTagJson = "{\n" +
                "    \"content\": \"content\",\n" +
                "    \"color\": \"#123456\"\n" +
                "}";

        //when
        mockMvc.perform(
                put("/tags/" + tag.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateTagJson)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(tag.getId()))
                .andExpect(jsonPath("$.content").value("content"))
                .andExpect(jsonPath("$.color").value("#123456"));
    }

    @Test
    void should_delete_requested_tag_when_called_delete_given_tag_id() throws Exception {
        //given
        Tag tag = new Tag("content", "#000000");
        this.tagRepository.insert(tag);

        //when
        mockMvc.perform(delete("/tags/" + tag.getId()))
                .andExpect(status().isNoContent());
        assertEquals(0, this.tagRepository.findAll().size());
    }
}
