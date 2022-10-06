package com.example.task_manager.service;

import com.example.task_manager.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User createUser(User user);
    User getUserById(long id);
    User updateUserById(User user, long id);
     void deleteUser(long id);
}
