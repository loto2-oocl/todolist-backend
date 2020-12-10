package com.oocl.todolist.service;

import com.oocl.todolist.model.Todo;
import com.oocl.todolist.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {
    @InjectMocks
    TodoService todoService;

    @Mock
    TodoRepository todoRepository;

    @Test
    void should_return_all_todos_when_get_all_given_repository_and_todos() {
        //given
        List<Todo> expected = Arrays.asList(new Todo(), new Todo());
        when(todoRepository.findAll()).thenReturn(expected);

        //when
        List<Todo> actual = todoService.getAll();

        //then
        assertEquals(actual, expected);
    }

    @Test
    void should_return_created_todo_when_create_with_todo_request() {
        //given
        Todo expected = new Todo("todo", false);
        when(todoRepository.insert(expected)).thenReturn(expected);

        //when
        Todo actual = todoService.create(expected);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_updated_todo_when_update_with_todo_id_and_todo_update() {
        //given
        String id = "1";
        Todo expected = new Todo(id, "message", false, Collections.emptyList());
        when(todoRepository.existsById(id)).thenReturn(true);
        when(todoRepository.save(expected)).thenReturn(expected);

        //when
        Todo actual = todoService.update(id, expected);

        //then
        verify(todoRepository, times(1)).save(expected);
        assertEquals(expected, actual);
    }

    @Test
    void should_call_delete_by_id_once_when_delete_given_todo_id() {
        //given
        String id = "1";

        //when
        todoService.delete(id);

        //then
        verify(todoRepository, times(1)).deleteById(id);
    }
}
