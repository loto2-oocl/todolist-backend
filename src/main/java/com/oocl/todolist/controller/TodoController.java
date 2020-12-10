package com.oocl.todolist.controller;

import com.oocl.todolist.model.Todo;
import com.oocl.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAll() {
        return this.todoService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo create(@RequestBody Todo createTodo) {
        return this.todoService.create(createTodo);
    }

    @PutMapping("/{todoId}")
    public Todo update(@PathVariable String todoId, @RequestBody Todo updateTodo) {
        return this.todoService.update(todoId, updateTodo);
    }

    @DeleteMapping("/{todoId}")
    public void delete(@PathVariable String todoId) {
    }
}
