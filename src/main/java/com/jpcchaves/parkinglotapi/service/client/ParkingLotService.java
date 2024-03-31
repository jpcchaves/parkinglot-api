package com.jpcchaves.parkinglotapi.service.client;

import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingResponseDTO;

public interface ParkingLotService {
  ParkingResponseDTO checkIn(ParkingCreateDTO parkingSpaceCreateDTO);
}
