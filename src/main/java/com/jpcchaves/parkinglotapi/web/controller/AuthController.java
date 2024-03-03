package com.jpcchaves.parkinglotapi.web.controller;

import com.jpcchaves.parkinglotapi.jwt.JwtToken;
import com.jpcchaves.parkinglotapi.jwt.JwtUserDetailsService;
import com.jpcchaves.parkinglotapi.web.dto.user.UserLoginDTO;
import com.jpcchaves.parkinglotapi.web.exception.ExceptionResponse;
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
