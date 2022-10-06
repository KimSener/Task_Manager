package com.example.task_manager.service;

import com.example.task_manager.exception.ResourceNotFoundException;
import com.example.task_manager.model.Task;
import com.example.task_manager.repository.TaskRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public Task creatTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task searchByTaskId(long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            return task.get();
        } else {
            throw new ResourceNotFoundException("Task", "Id", id);
        }
    }

    @Override
    public Task updateTaskById(Task task, long id) {
        Task existingTask = taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Task", "Id", id));
        existingTask.setNameTask(task.getNameTask());
        existingTask.setId(task.getId());
        existingTask.setDescription(task.getDescription());
        taskRepository.save(existingTask);
        return existingTask;
    }

    @Override
    public void deleteTaskById(long id) {
        taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Task", "ID", id));
        taskRepository.deleteById(id);
    }
}
