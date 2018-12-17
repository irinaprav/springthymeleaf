package com.alevel.thyme.thymetodo.todo;

import java.util.List;
import java.util.Optional;

public interface TodoOperations {
    List<Todo> getAllNotDone();

    Todo getTodo(String text);

    void updateAll(Iterable<Todo> todos);

    Long save(Todo todo);

    Optional<Todo> getById(Long id);
}
