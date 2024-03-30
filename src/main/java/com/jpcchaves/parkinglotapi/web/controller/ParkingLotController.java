package com.jpcchaves.parkinglotapi.web.controller;


import com.jpcchaves.parkinglotapi.service.client.ClientParkingSpaceService;
import com.jpcchaves.parkinglotapi.service.client.ParkingLotService;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/parking-lot")
public class ParkingLotController {
  private final ParkingLotService parkingLotService;
  private final ClientParkingSpaceService clientParkingSpaceService;

  public ParkingLotController(ParkingLotService parkingLotService,
                              ClientParkingSpaceService clientParkingSpaceService) {
    this.parkingLotService = parkingLotService;
    this.clientParkingSpaceService = clientParkingSpaceService;
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

  @GetMapping("/check-in/{receipt}")
  @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
  public ResponseEntity<ParkingResponseDTO> getByReceipt(@PathVariable(name = "receipt") String receipt) {
    return ResponseEntity.ok(clientParkingSpaceService.getByReceipt(receipt));
  }
}
