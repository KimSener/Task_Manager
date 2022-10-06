package com.example.task_manager.controller;

import com.example.task_manager.dto.UserDto;
import com.example.task_manager.model.User;

import com.example.task_manager.service.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {


    private final UserServiceImpl userService;

    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserServiceImpl userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public UserDto convertEntityToDto(User user) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        UserDto userDto = new UserDto();
        userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    public User convertDtoToEntity(UserDto userDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        User user = modelMapper.map(userDto, User.class);
        return user;
    }


    @GetMapping
    @ResponseBody
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<UserDto> getAllTasks() {
        return userService.getAllUsers()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

//   @PostMapping
//   @ResponseBody
//    public ResponseEntity <UserDto> createUser(@RequestBody UserDto userDto) {
//        User user = convertDtoToEntity(userDto);
//        User userSave = userService.createUser(user);
//        return new ResponseEntity<>(convertEntityToDto(userSave),HttpStatus.CREATED);
//    }

    @PostMapping("/addUser")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> creatUsers(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }


    @GetMapping("{id}")
    @ResponseBody
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") long userid) {
        return new ResponseEntity<>(convertEntityToDto(userService.getUserById(userid)), HttpStatus.OK);
    }

//    @PutMapping("{id}")
//    public ResponseEntity<UserDto> updateUser(@PathVariable("id") long id, @RequestBody UserDto userDto) {
//        User user = convertDtoToEntity(userDto);
//        User userUpdate = userService.updateUserById(user, id);
//        return new ResponseEntity<>(convertEntityToDto(userUpdate), HttpStatus.OK);
//
//    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUserById(user, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(" User deleted successfully", HttpStatus.OK);
    }


}
