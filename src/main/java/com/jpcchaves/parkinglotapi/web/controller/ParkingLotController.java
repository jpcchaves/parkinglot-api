package com.jpcchaves.parkinglotapi.web.controller;


import com.jpcchaves.parkinglotapi.service.client.ParkingLotService;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/parking-lot")
public class ParkingLotController {
  private final ParkingLotService parkingLotService;

  public ParkingLotController(ParkingLotService parkingLotService) {
    this.parkingLotService = parkingLotService;
  }

  @PostMapping("/check-in")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<ParkingResponseDTO> checkIn(@RequestBody @Valid
                                                    ParkingCreateDTO requestDTO) {
    return ResponseEntity.ok(parkingLotService.checkIn(requestDTO));
  }
}
