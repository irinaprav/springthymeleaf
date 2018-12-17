package com.alevel.thyme.thymetodo.todo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // реализует бизнес логику можно компонент но это более общий  а сервис говорит что именно тут бизнес логика
public class TodoService implements TodoOperations {

    public final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> getAllNotDone() {
        return  todoRepository.findByDoneFalse();
    }

    @Override
    public Todo getTodo(String text) {
        return todoRepository.getTodoByText(text);
    }

    @Override
    public void updateAll(Iterable<Todo> todos) {
        todoRepository.saveAll(todos);

    }

    @Override
    public Long save(Todo todo) {
        return todoRepository.save(todo).getId();
    }

    @Override
    public Optional<Todo> getById(Long id) {
        return todoRepository.findById(id);
    }
}
