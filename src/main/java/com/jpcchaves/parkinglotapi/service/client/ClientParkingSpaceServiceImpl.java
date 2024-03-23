package com.jpcchaves.parkinglotapi.service.client;

import com.jpcchaves.parkinglotapi.domain.models.ClientParkingSpace;
import com.jpcchaves.parkinglotapi.repository.ClientParkingSpaceRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientParkingSpaceServiceImpl implements ClientParkingSpaceService {

    private final ClientParkingSpaceRepository clientParkingSpaceRepository;

    public ClientParkingSpaceServiceImpl(ClientParkingSpaceRepository clientParkingSpaceRepository) {
        this.clientParkingSpaceRepository = clientParkingSpaceRepository;
    }

    @Override
    public ClientParkingSpace create(ClientParkingSpace clientParkingSpace) {
        return null;
    }
}
