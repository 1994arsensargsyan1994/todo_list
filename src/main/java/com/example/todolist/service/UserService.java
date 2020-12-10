package com.example.todolist.service;

import com.example.todolist.model.User;
import org.hibernate.metamodel.model.convert.internal.OrdinalEnumValueConverter;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(Long id);

    void save(User user);
}
