package com.alevel.thyme.thymetodo.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,  Long> {

    List<Todo> findByDoneFalse();
    Todo getTodoByText(String text);

}

//без реализации метода определит спринг что делать по названию метода он сам создать реализацию этого репозитория  в котором этот запрос реализован