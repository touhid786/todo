package com.touhid.todo.service.impl;

import com.touhid.todo.dto.TodoDto;
import com.touhid.todo.entity.Todo;
import com.touhid.todo.repository.TodoRepository;
import com.touhid.todo.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    public TodoServiceImpl(TodoRepository todoRepository, ModelMapper modelMapper) {
        this.todoRepository = todoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TodoDto createTodo(TodoDto todoDto) {
        Todo todo = modelMapper.map(todoDto, Todo.class);
        Todo saved = todoRepository.save(todo);
        return modelMapper.map(saved, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        return todoRepository.findAll()
                .stream()
                .map(todo -> modelMapper.map(todo, TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto getTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public TodoDto updateTodo(Long id, TodoDto todoDto) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        Todo updated = todoRepository.save(todo);
        return modelMapper.map(updated, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}
