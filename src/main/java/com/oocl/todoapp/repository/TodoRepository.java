package com.oocl.todoapp.repository;

import com.oocl.todoapp.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
}
