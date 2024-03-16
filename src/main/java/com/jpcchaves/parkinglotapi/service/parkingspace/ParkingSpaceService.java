package com.jpcchaves.parkinglotapi.service.parkingspace;

import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingSpaceCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingSpaceRespondeDTO;

public interface ParkingSpaceService {
    ParkingSpaceRespondeDTO create(ParkingSpaceCreateDTO requestDTO);
}
