package com.example.todolist.controller;

import com.example.todolist.model.User;
import com.example.todolist.model.payload.UserPayload;
import com.example.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public HttpStatus create(@RequestBody UserPayload userPayload) {

        User user = User.fromPayload(userPayload);
        userService.save(user);
        return HttpStatus.CREATED;

    }
}
