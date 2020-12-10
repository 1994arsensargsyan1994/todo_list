package com.example.todolist.Repository;

import com.example.todolist.model.Status;
import com.example.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {

    Optional<Task> findByIdAndArchive(Long id,Boolean archive);


    Optional<Task> findByIdAndArchiveAndStatus(Long id, Boolean archive, Status status);


   List<Task> findByArchiveAndStatus(Boolean archive, Status status);


}
