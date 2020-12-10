package com.oocl.todolist.integration;

import com.oocl.todolist.model.Tag;
import com.oocl.todolist.repository.TagRepository;
import com.oocl.todolist.service.TagService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
}
