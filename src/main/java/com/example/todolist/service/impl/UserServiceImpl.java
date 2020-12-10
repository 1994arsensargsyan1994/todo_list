package com.example.todolist.service.impl;

import com.example.todolist.Repository.UserRepository;
import com.example.todolist.model.User;
import com.example.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return  userRepository.findById(id);
    }

    @Override
    public void save(User user) {
     userRepository.save(user);
    }
}
