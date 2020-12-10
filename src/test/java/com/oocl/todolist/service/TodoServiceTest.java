package com.oocl.todolist.service;

import com.oocl.todolist.model.Todo;
import com.oocl.todolist.repository.TodoRepository;
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
}
