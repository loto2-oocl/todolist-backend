package com.oocl.todolist.mapper;

import com.oocl.todolist.dto.TodoRequest;
import com.oocl.todolist.dto.TodoResponse;
import com.oocl.todolist.model.Todo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {
    public Todo toEntity(TodoRequest todoRequest) {
        Todo todo = new Todo();

        BeanUtils.copyProperties(todoRequest, todo);

        return todo;
    }

    public TodoResponse toResponse(Todo todo) {
        TodoResponse todoResponse = new TodoResponse();

        BeanUtils.copyProperties(todo, todoResponse);

        return todoResponse;
    }
}
