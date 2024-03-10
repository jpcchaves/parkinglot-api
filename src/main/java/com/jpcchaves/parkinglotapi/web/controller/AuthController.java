package com.jpcchaves.parkinglotapi.web.controller;

import com.jpcchaves.parkinglotapi.jwt.JwtToken;
import com.jpcchaves.parkinglotapi.jwt.JwtUserDetailsService;
import com.jpcchaves.parkinglotapi.web.dto.user.UserLoginDTO;
import com.jpcchaves.parkinglotapi.web.dto.user.UserResponseDTO;
import com.jpcchaves.parkinglotapi.web.exception.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.logging.Logger;

@Tag(name = "Authetication", description = "Resource to allow users to deal with authentication in the API")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final static Logger logger = Logger.getLogger(AuthController.class.getName());
    private final JwtUserDetailsService jwtUserDetailsService;
    private final AuthenticationManager authenticationManager;

    public AuthController(JwtUserDetailsService jwtUserDetailsService,
                          AuthenticationManager authenticationManager) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @Operation(
            summary = "Authenticates a user",
            description = "Authenticates a user by passing an email and password",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User authenticated successfully!",
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
    @PostMapping(path = {"/signin", "/login"})
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO requestDTO, HttpServletRequest httpServletRequest) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword());
            authenticationManager.authenticate(authenticationToken);

            JwtToken jwtToken = jwtUserDetailsService.getTokenAuthenticated(requestDTO.getUsername());

            return ResponseEntity.ok(jwtToken);
        } catch (AuthenticationException ex) {
            logger.warning("Bad credentials from username %s".formatted(requestDTO.getUsername()));
        }

        return ResponseEntity.badRequest().body(new ExceptionResponse(new Date(),  "Credenciais invalidas", httpServletRequest.getRequestURI()));
    }
}
