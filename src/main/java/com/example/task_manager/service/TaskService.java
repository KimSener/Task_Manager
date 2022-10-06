package com.example.task_manager.service;

import com.example.task_manager.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTask();
    Task creatTask(Task task);
    Task searchByTaskId(long id);
    Task updateTaskById(Task task,long id);
    void deleteTaskById(long id);


}
