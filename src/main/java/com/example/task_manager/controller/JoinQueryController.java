package com.example.task_manager.controller;

import com.example.task_manager.dto.JoinTableUserAndTaskDto;
import com.example.task_manager.service.JoinQueryUserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Join")
public class JoinQueryController {

    private final JoinQueryUserTaskService joinQueryUserTaskService;

    @Autowired
    public JoinQueryController(JoinQueryUserTaskService joinQueryUserTaskService) {
        this.joinQueryUserTaskService = joinQueryUserTaskService;
    }

    @GetMapping("/JoinInner")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<JoinTableUserAndTaskDto> getJoinInnerUserTaskData() {
        return joinQueryUserTaskService.getUserTaskDataInnerJoin();
    }

    @GetMapping("/JoinLeft")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<JoinTableUserAndTaskDto> getJoinLeftUserTaskData() {
        return joinQueryUserTaskService.getUserTaskDataLeftJoin();

    }


}
