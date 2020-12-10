package com.oocl.todolist.service;

import com.oocl.todolist.model.Todo;
import com.oocl.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAll() {
        return this.todoRepository.findAll();
    }

    public Todo create(Todo newTodo) {
        return this.todoRepository.insert(newTodo);
    }

    public Todo update(String todoId, Todo updateTodo) {
        if (!this.todoRepository.existsById(todoId)) {
            throw new RuntimeException();
        }

        return this.todoRepository.save(updateTodo);
    }

    public void delete(String todoId) {
    }
}
