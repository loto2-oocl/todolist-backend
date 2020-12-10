package com.oocl.todolist.service;

import com.oocl.todolist.exception.TagNotFoundException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
        List<Tag> actual = tagService.getAll();

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

    @Test
    void should_return_updated_tag_when_update_given_repository_and_tag_update_and_tag_id() {
        //given
        String id = "1";
        Tag newTag = new Tag(id, "new content", "#111111");
        when(tagRepository.existsById(id)).thenReturn(true);
        when(tagRepository.save(newTag)).thenReturn(newTag);

        //when
        Tag actual = tagService.update(id, newTag);

        //then
        verify(tagRepository, times(1)).save(newTag);
        assertEquals(newTag, actual);
    }

    @Test
    void should_throw_tag_not_found_exception_when_update_given_tag_id_not_exists() {
        //given
        String id = "1";
        when(tagRepository.existsById(id)).thenReturn(false);

        //then
        assertThrows(
                TagNotFoundException.class,
                () -> {
                    tagService.update(id, new Tag());
                },
                "Tag with id:1 cannot be found"
        );
    }

    @Test
    void should_call_delete_by_id_once_when_delete_given_tag_id() {
        //given
        String id = "1";

        //when
        tagService.delete(id);

        //then
        verify(tagRepository, times(1)).deleteById(id);
    }
}
