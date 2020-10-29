package com.oocl.todoapp.repository;

import com.oocl.todoapp.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByDone(boolean done);
}
