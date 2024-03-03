package com.jpcchaves.parkinglotapi.web.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {
    @NotBlank(message = "O email e obrigatorio!")
    @Email(message = "Email invalido!")
    private String username;

    @NotBlank(message = "A senha e obrigatoria")
    @Size(min = 6, message = "O campo precisa ter pelo menos 6 caracteres")
    private String password;

    public UserLoginDTO() {
    }

    public UserLoginDTO(String username,
                        String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
