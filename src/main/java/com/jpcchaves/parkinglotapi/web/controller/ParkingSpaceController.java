package com.jpcchaves.parkinglotapi.web.controller;

import com.jpcchaves.parkinglotapi.service.parkingspace.ParkingSpaceService;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingSpaceCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingSpaceRespondeDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/parking-spaces")
public class ParkingSpaceController {
    private final ParkingSpaceService parkingSpaceService;

    public ParkingSpaceController(ParkingSpaceService parkingSpaceService) {
        this.parkingSpaceService = parkingSpaceService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ParkingSpaceRespondeDTO> create(@RequestBody @Valid
                                                          ParkingSpaceCreateDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpaceService.create(requestDTO));
    }

}
