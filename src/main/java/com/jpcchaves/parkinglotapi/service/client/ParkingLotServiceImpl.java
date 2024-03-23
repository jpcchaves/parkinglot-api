package com.jpcchaves.parkinglotapi.service.client;

import com.jpcchaves.parkinglotapi.repository.ClientParkingSpaceRepository;
import com.jpcchaves.parkinglotapi.repository.ClientRepository;
import com.jpcchaves.parkinglotapi.repository.ParkingSpaceRepository;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotServiceImpl implements ParkingLotService {
    private final ClientParkingSpaceRepository clientParkingSpaceRepository;
    private final ClientRepository clientRepository;
    private final ParkingSpaceRepository parkingSpaceRepository;

    public ParkingLotServiceImpl(ClientParkingSpaceRepository clientParkingSpaceRepository,
                                 ClientRepository clientRepository,
                                 ParkingSpaceRepository parkingSpaceRepository) {
        this.clientParkingSpaceRepository = clientParkingSpaceRepository;
        this.clientRepository = clientRepository;
        this.parkingSpaceRepository = parkingSpaceRepository;
    }
}
