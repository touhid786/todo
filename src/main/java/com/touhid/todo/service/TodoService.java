package com.touhid.todo.service;

import com.touhid.todo.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto createTodo(TodoDto todoDto);
    List<TodoDto> getAllTodos();
    TodoDto getTodoById(Long id);
    TodoDto updateTodo(Long id, TodoDto todoDto);
    void deleteTodo(Long id);
}
