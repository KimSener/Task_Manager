package com.example.task_manager.service;

import com.example.task_manager.dto.JoinTableUserAndTaskDto;
import com.example.task_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoinQueryUserTaskService {


    private final UserRepository userRepository;

    @Autowired
    public JoinQueryUserTaskService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<JoinTableUserAndTaskDto> getUserTaskDataInnerJoin() {
        return userRepository.getJoinInnerTable();
    }

    public List<JoinTableUserAndTaskDto> getUserTaskDataLeftJoin() {
        return userRepository.getJoinLeftTable();
    }

}


