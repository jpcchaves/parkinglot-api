package com.jpcchaves.parkinglotapi.service.client;

import com.jpcchaves.parkinglotapi.domain.models.ClientParkingSpace;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingResponseDTO;

public interface ClientParkingSpaceService {
  ClientParkingSpace create(ClientParkingSpace clientParkingSpace);

  ParkingResponseDTO getByReceipt(String receipt);

  ParkingResponseDTO checkout(String receipt);
}
