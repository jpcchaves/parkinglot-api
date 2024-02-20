package com.jpcchaves.parkinglotapi.web.controller;

import com.jpcchaves.parkinglotapi.domain.models.User;
import com.jpcchaves.parkinglotapi.service.user.UserService;
import com.jpcchaves.parkinglotapi.web.dto.user.UserCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.user.UserResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserCreateDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(requestDTO));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "userId") Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<User> updateUserPassword(@PathVariable(name = "userId") Long userId, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUserPassword(userId, user.getPassword()));
    }

    @GetMapping
    public ResponseEntity<List<User>> listAllUsers() {
        return ResponseEntity.ok(userService.listAllUsers());
    }
}
