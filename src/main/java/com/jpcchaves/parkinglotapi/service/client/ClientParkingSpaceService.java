package com.jpcchaves.parkinglotapi.service.client;

import com.jpcchaves.parkinglotapi.domain.models.ClientParkingSpace;
import com.jpcchaves.parkinglotapi.repository.projection.ClientParkingProjection;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientParkingSpaceService {
  ClientParkingSpace create(ClientParkingSpace clientParkingSpace);

  ParkingResponseDTO getByReceipt(String receipt);

  ParkingResponseDTO checkout(String receipt);

  Page<ClientParkingProjection> getAllByCpf(String cpf,
                                            Pageable pageable);
}
