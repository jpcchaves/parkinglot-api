package com.jpcchaves.parkinglotapi.web.dto.client;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public class ClientCreateDTO {

    @NotNull(message = "O nome e obrigatorio")
    @Size(min = 5, max = 100)
    private String name;

    @NotNull(message = "O CPF e obrigatorio")
    @Size(min = 11, max = 11)
    @CPF(message = "CPF invalido")
    private String cpf;

    public ClientCreateDTO() {
    }

    public ClientCreateDTO(String name,
                           String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
