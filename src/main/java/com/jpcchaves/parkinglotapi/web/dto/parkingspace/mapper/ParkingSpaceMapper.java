package com.jpcchaves.parkinglotapi.web.dto.parkingspace.mapper;

import com.jpcchaves.parkinglotapi.domain.models.ParkingSpace;
import com.jpcchaves.parkinglotapi.uitls.mapper.MapperUtils;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingSpaceCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingSpaceRespondeDTO;
import org.springframework.stereotype.Component;

@Component
public class ParkingSpaceMapper {
    private final MapperUtils mapperUtils;

    public ParkingSpaceMapper(MapperUtils mapperUtils) {
        this.mapperUtils = mapperUtils;
    }

    public ParkingSpace toParkingSpace(ParkingSpaceCreateDTO requestDTO) {
        return mapperUtils.parseObject(requestDTO, ParkingSpace.class);
    }

    public ParkingSpaceRespondeDTO toParkingSpaceResponseDTO(ParkingSpace parkingSpace) {
        return mapperUtils.parseObject(parkingSpace, ParkingSpaceRespondeDTO.class);
    }
}
