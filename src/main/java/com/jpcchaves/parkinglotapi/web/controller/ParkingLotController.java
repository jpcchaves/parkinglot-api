package com.jpcchaves.parkinglotapi.web.controller;


import com.jpcchaves.parkinglotapi.jwt.JwtUserDetails;
import com.jpcchaves.parkinglotapi.repository.projection.ClientParkingProjection;
import com.jpcchaves.parkinglotapi.service.client.ClientParkingSpaceService;
import com.jpcchaves.parkinglotapi.service.client.ParkingLotService;
import com.jpcchaves.parkinglotapi.uitls.mapper.MapperUtils;
import com.jpcchaves.parkinglotapi.web.dto.PageableDTO;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/parking-lot")
public class ParkingLotController {
  private final ParkingLotService parkingLotService;
  private final ClientParkingSpaceService clientParkingSpaceService;
  private final MapperUtils mapperUtils;

  public ParkingLotController(ParkingLotService parkingLotService,
                              ClientParkingSpaceService clientParkingSpaceService,
                              MapperUtils mapperUtils) {
    this.parkingLotService = parkingLotService;
    this.clientParkingSpaceService = clientParkingSpaceService;
    this.mapperUtils = mapperUtils;
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

  @PutMapping("/check-out/{receipt}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<ParkingResponseDTO> checkout(@PathVariable(name = "receipt") String receipt) {
    return ResponseEntity.ok(clientParkingSpaceService.checkout(receipt));
  }

  @GetMapping("/cpf/{cpf}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<PageableDTO<?>> getAllParkingsByCpf(@PathVariable(name = "cpf") String cpf,
                                                            @PageableDefault(size = 5, sort = "entryDate", direction = Sort.Direction.ASC)
                                                            Pageable pageable) {
    Page<ClientParkingProjection> projection = clientParkingSpaceService.getAllByCpf(cpf, pageable);
    PageableDTO<?> pageableDTO = mapperUtils.parseObject(projection, PageableDTO.class);
    return ResponseEntity.ok(pageableDTO);
  }

  @GetMapping
  @PreAuthorize("hasRole('CLIENT')")
  public ResponseEntity<PageableDTO<?>> getAllParkingsFromClient(@AuthenticationPrincipal JwtUserDetails user,
                                                                 @PageableDefault(size = 5, sort = "entryDate", direction = Sort.Direction.ASC)
                                                                 Pageable pageable) {
    Page<ClientParkingProjection> projection = clientParkingSpaceService.getAllByUserId(user.getId(), pageable);
    PageableDTO<?> pageableDTO = mapperUtils.parseObject(projection, PageableDTO.class);
    return ResponseEntity.ok(pageableDTO);
  }
}
