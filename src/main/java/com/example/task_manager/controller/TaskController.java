package com.example.task_manager.controller;

import com.example.task_manager.dto.TaskDto;
import com.example.task_manager.model.Task;
import com.example.task_manager.service.TaskServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskServiceImpl taskService;

    private final ModelMapper modelMapper;

    @Autowired
    public TaskController(TaskServiceImpl taskService, ModelMapper modelMapper) {
        this.taskService = taskService;
        this.modelMapper = modelMapper;
    }

    public TaskDto convertEntityToDto(Task task) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        TaskDto taskDto = new TaskDto();
        taskDto = modelMapper.map(task, TaskDto.class);
        return taskDto;
    }

    public Task convertDtoToEntity(TaskDto taskDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Task task = modelMapper.map(taskDto, Task.class);
        return task;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTask()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/addTask")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        Task task = convertDtoToEntity(taskDto);
        Task taskSave = taskService.creatTask(task);
        return new ResponseEntity<>(convertEntityToDto(taskSave), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable("id") long taskid) {
        return new ResponseEntity<>(convertEntityToDto(taskService.searchByTaskId(taskid)), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaskDto> updateTask(@PathVariable("id") long id, @RequestBody TaskDto taskDto) {
        Task task = convertDtoToEntity(taskDto);
        Task taskUpdate = taskService.updateTaskById(task, id);
        return new ResponseEntity<>(convertEntityToDto(taskUpdate), HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteTask(@PathVariable("id") long id) {
        taskService.deleteTaskById(id);
        return new ResponseEntity<>(" Task deleted successfully", HttpStatus.OK);
    }


}
