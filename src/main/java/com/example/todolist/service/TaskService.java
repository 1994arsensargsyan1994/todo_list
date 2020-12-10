package com.example.todolist.service;

import com.example.todolist.model.Status;
import com.example.todolist.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Task create(Task task);

    List<Task> findAll();

    Optional<Task> findTask(Long id, Boolean archive);
    Optional<Task> findTask(Long id, Boolean b, Status status);


    Task update(Task taskFromDb, Task taskFromPayload);

    void delete(Task task);


    List<Task> findByStatus(String statusParam, Boolean archive);
}
