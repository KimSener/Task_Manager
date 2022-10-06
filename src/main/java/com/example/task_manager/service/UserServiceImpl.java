package com.example.task_manager.service;

import com.example.task_manager.exception.ResourceNotFoundException;
import com.example.task_manager.model.User;
import com.example.task_manager.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getAllUsers() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;

    }

    @Override
    public User createUser(User user) {
        User result = userRepository.save(user);
        log.info("IN create - user: {}", user);
        return result;
    }

    @Override
    public User getUserById(long id) {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            log.info("IN findById - user: {} found by id: {}", result);
            return result.get();
        } else {
            log.warn("IN findById - no user found by id: {}", id);
            throw new ResourceNotFoundException("User", "Id", id);
        }
    }

    @Override
    public User updateUserById(User user, long id) {
        User existingUser = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", id));
        log.info("IN updateUserById - user: {} found by id: {}", existingUser);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setTask(user.getTask());
        userRepository.save(existingUser);
        return existingUser;
    }

    @Override
    public void deleteUser(long id) {
        userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "ID", id));
        log.info("IN delete - user with id: {} successfully deleted");
        userRepository.deleteById(id);
    }


}
