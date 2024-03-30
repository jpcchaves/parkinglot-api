package com.jpcchaves.parkinglotapi.service.client;

import com.jpcchaves.parkinglotapi.domain.Enum.ParkingSpaceStatus;
import com.jpcchaves.parkinglotapi.domain.models.Client;
import com.jpcchaves.parkinglotapi.domain.models.ClientParkingSpace;
import com.jpcchaves.parkinglotapi.domain.models.ParkingSpace;
import com.jpcchaves.parkinglotapi.exception.EntityNotFoundException;
import com.jpcchaves.parkinglotapi.exception.NoParkingSpaceAvailableExeception;
import com.jpcchaves.parkinglotapi.repository.ClientParkingSpaceRepository;
import com.jpcchaves.parkinglotapi.repository.ClientRepository;
import com.jpcchaves.parkinglotapi.repository.ParkingSpaceRepository;
import com.jpcchaves.parkinglotapi.util.ParkingSpaceUtils;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingSpaceCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingSpaceResponseDTO;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.mapper.ClientParkingSpaceMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
  private final ClientParkingSpaceRepository clientParkingSpaceRepository;
  private final ClientRepository clientRepository;
  private final ParkingSpaceRepository parkingSpaceRepository;
  private final ClientParkingSpaceMapper clientParkingSpaceMapper;

  public ParkingLotServiceImpl(ClientParkingSpaceRepository clientParkingSpaceRepository,
                               ClientRepository clientRepository,
                               ParkingSpaceRepository parkingSpaceRepository,
                               ClientParkingSpaceMapper clientParkingSpaceMapper) {
    this.clientParkingSpaceRepository = clientParkingSpaceRepository;
    this.clientRepository = clientRepository;
    this.parkingSpaceRepository = parkingSpaceRepository;
    this.clientParkingSpaceMapper = clientParkingSpaceMapper;
  }

  @Transactional
  public ParkingSpaceResponseDTO checkIn(ParkingSpaceCreateDTO parkingSpaceCreateDTO) {
    ClientParkingSpace clientParkingSpace = clientParkingSpaceMapper.toClientParkingSpace(parkingSpaceCreateDTO);
    Client client = getClientByCpf(clientParkingSpace.getClient().getCpf());
    clientParkingSpace.setClient(client);

    ParkingSpace parkingSpace = findFreeParkingSpace();
    parkingSpace.setStatus(ParkingSpaceStatus.OCCUPIED);

    clientParkingSpace.setEntryDate(LocalDateTime.now());
    clientParkingSpace.setReceipt(ParkingSpaceUtils.generateReceipt());

    return clientParkingSpaceMapper.toDto(clientParkingSpaceRepository.save(clientParkingSpace));
  }

  @Transactional(readOnly = true)
  public Client getClientByCpf(String cpf) {
    return clientRepository
        .findClientByCpf(cpf)
        .orElseThrow(() -> new EntityNotFoundException("No client found with the given CPF"));
  }

  @Transactional(readOnly = true)
  public ParkingSpace findFreeParkingSpace() {
    return parkingSpaceRepository
        .findFirstByStatus(ParkingSpaceStatus.AVAILABLE)
        .orElseThrow(() -> new NoParkingSpaceAvailableExeception("There is no parking space available"));
  }
}
