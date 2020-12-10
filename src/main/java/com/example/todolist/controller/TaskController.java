package com.example.todolist.controller;

import com.example.todolist.model.Status;
import com.example.todolist.model.Task;
import com.example.todolist.model.User;
import com.example.todolist.model.payload.TaskRequestCreatePayload;
import com.example.todolist.model.payload.TaskRequestUpdatePayload;
import com.example.todolist.service.TaskService;
import com.example.todolist.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;


    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @PostMapping
    public HttpStatus create(@RequestBody TaskRequestCreatePayload taskRequest) {

        Task task = Task.fromCreatePayload(taskRequest);
        Long userId = taskRequest.getUserId();
        getUserId(task, userId);
        Task taskFromDb = taskService.create(task);
        if (taskFromDb != null) {
            return HttpStatus.CREATED;
        }
        return HttpStatus.BAD_REQUEST;

    }

    private void getUserId(Task task, Long userId) {
        if (userId != null) {
            Optional<User> userOptional = userService.findUserById(userId);
            if (userOptional.isPresent()) {
                task.setUserId(userOptional.get());
            }
        }
    }

    @PostMapping("/done/{id}")
    public ResponseEntity doneTask(@RequestBody TaskRequestUpdatePayload updatePayload, @PathVariable Long id) {
        Optional<Task> taskOptional = taskService.findTask(id, false, Status.TODO);
        if (taskOptional.isEmpty()) {
            return ResponseEntity.ok(HttpStatus.NOT_FOUND);
        }
        Task taskDone = getTaskDone(updatePayload, taskOptional.get());

        return ResponseEntity.ok(taskService.create(taskDone));

    }

    @GetMapping
    public ResponseEntity<List<Task>> findAll() {
        return ResponseEntity.ok(taskService.findAll());
    }


    @GetMapping("/getTodoOrDoneTasks")
    public ResponseEntity getTaskByStatus(@RequestParam String status) {
        List<Task> tasks = taskService.findByStatus(status, false);
        if (tasks != null) {
            return ResponseEntity.ok(tasks);
        }
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }


    @PatchMapping("/delete/{id}")
    public HttpStatus delete(@RequestBody TaskRequestUpdatePayload updatePayload, @RequestParam(required = false) String archive, @PathVariable Long id) {
        Optional<Task> taskOptional = taskService.findTask(id, false);
        if (taskOptional.isEmpty()) {
            return HttpStatus.NOT_FOUND;
        }
        if (archive != null && archive.equalsIgnoreCase("true")) {
            Task task = getTaskArchive(updatePayload, taskOptional.get());
            taskService.create(task);
            return HttpStatus.OK;
        }
        taskService.delete(taskOptional.get());
        return HttpStatus.OK;

    }

    private Task getTaskArchive(@RequestBody TaskRequestUpdatePayload updatePayload, Task task) {

        task.setArchive(true);
        task.setArchiveDateTime(updatePayload.getArchiveDateTime());
        task.setId(task.getId());
        return task;
    }

    private Task getTaskDone(@RequestBody TaskRequestUpdatePayload updatePayload, Task task) {

        task.setDoneTime(updatePayload.getDoneTime());
        task.setTrackingTime(updatePayload.getTrackingTime());
        task.setStatus(Status.DONE);
        task.setId(task.getId());
        return task;
    }
}
