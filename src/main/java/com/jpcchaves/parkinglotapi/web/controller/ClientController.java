package com.jpcchaves.parkinglotapi.web.controller;

import com.jpcchaves.parkinglotapi.jwt.JwtUserDetails;
import com.jpcchaves.parkinglotapi.service.client.ClientService;
import com.jpcchaves.parkinglotapi.web.dto.PageableDTO;
import com.jpcchaves.parkinglotapi.web.dto.client.ClientCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.client.ClientResponseDTO;
import com.jpcchaves.parkinglotapi.web.dto.user.UserResponseDTO;
import com.jpcchaves.parkinglotapi.web.exception.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(
            security = @SecurityRequirement(name = "security"),
            summary = "Creates a client",
            description = "Creates a new client related to an user",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Client created successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ClientResponseDTO.class
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
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ClientResponseDTO> create(@Valid @RequestBody ClientCreateDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.create(requestDTO));
    }

    @Operation(
            security = @SecurityRequirement(name = "security"),
            summary = "Find client by id",
            description = "Find a client by passing its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Gets the client by id",
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
    @GetMapping("/{clientId}")
    public ResponseEntity<ClientResponseDTO> getById(@PathVariable(name = "clientId") Long clientId) {
        return ResponseEntity.ok(clientService.getById(clientId));
    }

    @Operation(
            security = @SecurityRequirement(name = "security"),
            summary = "Gets the users list",
            description = "Gets the users list",
            parameters = {
                    @Parameter(
                            in = ParameterIn.QUERY, name = "page",
                            content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY, name = "size",
                            content = @Content(schema = @Schema(type = "integer", defaultValue = "20"))
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY, name = "sort",
                            hidden = true,
                            content = @Content(schema = @Schema(type = "string", defaultValue = "id,asc"))
                    )
            },
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
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized! User needs to authenticate to access this resource",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ExceptionResponse.class
                                    ))
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbbiden! User has no privileges to access this resource",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ExceptionResponse.class
                                    ))
                    ),
            }
    )
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageableDTO<?>> getAll(@Parameter(hidden = true) @PageableDefault(size = 5, sort = {"name"}) Pageable pageable) {
        return ResponseEntity.ok(clientService.getClientsList(pageable));
    }

    @GetMapping("/details")
    public ResponseEntity<ClientResponseDTO> getClientDetails(@AuthenticationPrincipal
                                                              JwtUserDetails userDetails) {
        return ResponseEntity.ok(clientService.getClientDetails(userDetails));
    }
}
