package com.example.todolist.service.impl;

import com.example.todolist.Repository.TaskRepository;
import com.example.todolist.model.Status;
import com.example.todolist.model.Task;
import com.example.todolist.service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;


    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> findTask(Long id, Boolean archive) {
        return taskRepository.findByIdAndArchive(id, archive);
    }

    @Override
    public Optional<Task> findTask(Long id, Boolean b, Status status) {
        return taskRepository.findByIdAndArchiveAndStatus(id, b, status);
    }

    @Override
    public Task update(Task taskFromDb, Task taskFromPayload) {
        BeanUtils.copyProperties(taskFromDb, taskFromPayload, "id");
        return taskFromPayload;
    }

    @Override
    public void delete(Task task) {
        taskRepository.delete(task);
    }

    @Override
    public List<Task> findByStatus(String statusParam, Boolean archive) {


        Status status = Status.valueOf(statusParam);
        if (status.ordinal() == 0 || status.ordinal() == 1) {
            return taskRepository.findByArchiveAndStatus(archive, status);
        }
        return null;
    }
}
