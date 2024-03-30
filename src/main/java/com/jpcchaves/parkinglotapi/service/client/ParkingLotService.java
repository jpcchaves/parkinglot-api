package com.jpcchaves.parkinglotapi.service.client;

import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingSpaceCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingSpaceResponseDTO;

public interface ParkingLotService {
  ParkingSpaceResponseDTO checkIn(ParkingSpaceCreateDTO parkingSpaceCreateDTO);
}
