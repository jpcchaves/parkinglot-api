package com.jpcchaves.parkinglotapi.web.controller;

import com.jpcchaves.parkinglotapi.service.user.UserService;
import com.jpcchaves.parkinglotapi.web.dto.user.UserCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.user.UserResponseDTO;
import com.jpcchaves.parkinglotapi.web.dto.user.UserUpdatePasswordDTO;
import com.jpcchaves.parkinglotapi.web.exception.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "Controller to manage users")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Creates a new user",
            description = "Creates a new user by passing its JSON representation",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Usuario criado com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UserResponseDTO.class
                                    ))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ExceptionResponse.class
                                    ))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ExceptionResponse.class
                                    ))
                    ),
            }
    )
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreateDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(requestDTO));
    }

    @Operation(
            summary = "Find user by id",
            description = "Find a new user by passing its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Gets the user by id",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UserResponseDTO.class
                                    ))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ExceptionResponse.class
                                    ))
                    ),
            }
    )
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable(name = "userId") Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @Operation(
            summary = "Updates user password",
            description = "Updates user password",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Updates user password",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema()
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ExceptionResponse.class
                                    ))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ExceptionResponse.class
                                    ))
                    ),
            }
    )
    @PatchMapping("/{userId}")
    public ResponseEntity<Void> updateUserPassword(@PathVariable(name = "userId") Long userId, @Valid @RequestBody UserUpdatePasswordDTO requestDTO) {
        userService.updateUserPassword(userId, requestDTO);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Gets the users list",
            description = "Gets the users list",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Gets the users list",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = UserResponseDTO.class
                                            )
                                    ))
                    ),
            }
    )
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listAllUsers() {
        return ResponseEntity.ok(userService.listAllUsers());
    }
}
