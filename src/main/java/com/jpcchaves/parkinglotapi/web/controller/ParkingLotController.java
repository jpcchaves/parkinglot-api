package com.jpcchaves.parkinglotapi.web.controller;


import com.jpcchaves.parkinglotapi.service.client.ParkingLotService;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

  @Operation(
      summary = "Check in of a car",
      description = "Check in when a car enters the parking lot",
      security = @SecurityRequirement(name = "security"),
      responses = {
          @ApiResponse(responseCode = "404", description = "Bad request")
      }

  )
  @PostMapping("/check-in")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<ParkingResponseDTO> checkIn(@RequestBody @Valid
                                                    ParkingCreateDTO requestDTO) {
    return ResponseEntity.ok(parkingLotService.checkIn(requestDTO));
  }
}
