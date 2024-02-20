package com.jpcchaves.parkinglotapi.web.controller;

import com.jpcchaves.parkinglotapi.service.user.UserService;
import com.jpcchaves.parkinglotapi.web.dto.user.UserCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.user.UserResponseDTO;
import com.jpcchaves.parkinglotapi.web.dto.user.UserUpdatePasswordDTO;
import jakarta.validation.Valid;
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
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreateDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(requestDTO));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable(name = "userId") Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Void> updateUserPassword(@PathVariable(name = "userId") Long userId, @Valid @RequestBody UserUpdatePasswordDTO requestDTO) {
        userService.updateUserPassword(userId, requestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listAllUsers() {
        return ResponseEntity.ok(userService.listAllUsers());
    }
}
