package com.oocl.todolist.service;

import com.oocl.todolist.model.Tag;
import com.oocl.todolist.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TagServiceTest {
    @InjectMocks
    TagService tagService;

    @Mock
    TagRepository tagRepository;

    @Test
    void should_return_all_tags_when_get_all_given_repository() {
        //given
        List<Tag> expected = Arrays.asList(new Tag(), new Tag());
        when(tagRepository.findAll()).thenReturn(expected);

        //when
        List<Tag> actual =tagService.getAll();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_created_tag_when_create_given_repository_and_tag_request() {
        //given
        Tag expected = new Tag("1", "tag", "#000000");
        when(tagRepository.insert(expected)).thenReturn(expected);

        //when
        Tag actual = tagService.create(expected);

        //then
        assertEquals(expected, actual);
    }
}
