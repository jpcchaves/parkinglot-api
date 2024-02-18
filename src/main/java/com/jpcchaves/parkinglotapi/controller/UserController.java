package com.jpcchaves.parkinglotapi.controller;

import com.jpcchaves.parkinglotapi.domain.models.User;
import com.jpcchaves.parkinglotapi.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "userId") Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}
