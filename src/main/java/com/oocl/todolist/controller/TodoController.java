package com.oocl.todolist.controller;

import com.oocl.todolist.dto.TodoRequest;
import com.oocl.todolist.dto.TodoResponse;
import com.oocl.todolist.mapper.TodoMapper;
import com.oocl.todolist.model.Todo;
import com.oocl.todolist.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;
    private final TodoMapper todoMapper;

    public TodoController(TodoService todoService, TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }

    @GetMapping
    public List<TodoResponse> getAll() {
        return this.todoService.getAll().stream()
                .map(todoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse create(@RequestBody TodoRequest createTodoRequest) {
        Todo createTodo = this.todoMapper.toEntity(createTodoRequest);
        Todo createdTodo = this.todoService.create(createTodo);

        return this.todoMapper.toResponse(createdTodo);
    }

    @PutMapping("/{todoId}")
    public TodoResponse update(@PathVariable String todoId, @RequestBody TodoRequest updateTodoRequest) {
        Todo updateTodo = this.todoMapper.toEntity(updateTodoRequest);
        Todo updatedTodo = this.todoService.update(todoId, updateTodo);

        return this.todoMapper.toResponse(updatedTodo);
    }

    @DeleteMapping("/{todoId}")
    public void delete(@PathVariable String todoId) {
        this.todoService.delete(todoId);
    }
}
