package com.jpcchaves.parkinglotapi.service.parkingspace;

import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingSpaceCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingSpaceResponseDTO;

public interface ParkingSpaceService {
    ParkingSpaceResponseDTO create(ParkingSpaceCreateDTO requestDTO);

    ParkingSpaceResponseDTO getByCode(String code);
}
