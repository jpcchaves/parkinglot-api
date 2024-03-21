package com.jpcchaves.parkinglotapi.web.dto.parkingspace.mapper;

import com.jpcchaves.parkinglotapi.domain.models.ClientParkingSpace;
import com.jpcchaves.parkinglotapi.uitls.mapper.MapperUtils;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingSpaceCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingSpaceResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ClientParkingSpaceMapper {

    private final MapperUtils mapperUtils;

    public ClientParkingSpaceMapper(MapperUtils mapperUtils) {
        this.mapperUtils = mapperUtils;
    }

    public ClientParkingSpace toClientParkingSpace(ParkingSpaceCreateDTO requestDTO) {
        return mapperUtils.parseObject(requestDTO, ClientParkingSpace.class);
    }

    public ParkingSpaceResponseDTO toDto(ClientParkingSpace clientParkingSpace) {
        return mapperUtils.parseObject(clientParkingSpace, ParkingSpaceResponseDTO.class);
    }
}
