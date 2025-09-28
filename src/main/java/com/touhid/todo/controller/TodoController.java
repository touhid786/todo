package com.touhid.todo.controller;

import com.touhid.todo.dto.TodoDto;
import com.touhid.todo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto) {
        return ResponseEntity.ok(todoService.createTodo(todoDto));
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.getTodoById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id, @RequestBody TodoDto todoDto) {
        return ResponseEntity.ok(todoService.updateTodo(id, todoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}
