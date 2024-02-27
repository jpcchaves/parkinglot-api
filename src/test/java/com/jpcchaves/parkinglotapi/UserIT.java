package com.jpcchaves.parkinglotapi;

import com.jpcchaves.parkinglotapi.web.dto.user.UserCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.user.UserResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(
        scripts = "/sql/users/users-insert.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
@Sql(
        scripts = "/sql/users/users-delete.sql",
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS
)
public class UserIT {

    @Autowired
    WebTestClient testClient;

    @Test
    public void createUsuario() {
        UserResponseDTO response = testClient
                .post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserCreateDTO("testeIT@email.com", "123456"))
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(UserResponseDTO.class)
                .returnResult()
                .getResponseBody();


        assert response != null;
        Assertions.assertEquals(response.getUsername(), "testeIT@email.com");
        Assertions.assertEquals(response.getRole(), "CLIENT");
    }
}
