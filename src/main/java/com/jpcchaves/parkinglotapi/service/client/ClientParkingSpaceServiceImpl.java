package com.jpcchaves.parkinglotapi.service.client;

import com.jpcchaves.parkinglotapi.domain.Enum.ParkingSpaceStatus;
import com.jpcchaves.parkinglotapi.domain.models.ClientParkingSpace;
import com.jpcchaves.parkinglotapi.exception.EntityNotFoundException;
import com.jpcchaves.parkinglotapi.repository.ClientParkingSpaceRepository;
import com.jpcchaves.parkinglotapi.uitls.mapper.MapperUtils;
import com.jpcchaves.parkinglotapi.util.ParkingUtils;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ClientParkingSpaceServiceImpl implements ClientParkingSpaceService {

  private final ClientParkingSpaceRepository clientParkingSpaceRepository;
  private final MapperUtils mapperUtils;

  public ClientParkingSpaceServiceImpl(ClientParkingSpaceRepository clientParkingSpaceRepository,
                                       MapperUtils mapperUtils) {
    this.clientParkingSpaceRepository = clientParkingSpaceRepository;
    this.mapperUtils = mapperUtils;
  }

  @Override
  public ClientParkingSpace create(ClientParkingSpace clientParkingSpace) {
    return clientParkingSpaceRepository.save(clientParkingSpace);
  }

  @Override
  @Transactional(readOnly = true)
  public ParkingResponseDTO getByReceipt(String receipt) {

    ClientParkingSpace clientParkingSpace = clientParkingSpaceRepository
        .findByReceiptAndExitDateIsNull(receipt)
        .orElseThrow(() -> new EntityNotFoundException(String.format("Receipt not found %s", receipt)));

    return mapperUtils.parseObject(clientParkingSpace, ParkingResponseDTO.class);
  }

  @Override
  @Transactional
  public ParkingResponseDTO checkout(String receipt) {
    ClientParkingSpace clientParkingSpace = clientParkingSpaceRepository.findByReceiptAndExitDateIsNull(receipt)
        .orElseThrow(() -> new EntityNotFoundException(""));

    LocalDateTime exitDate = LocalDateTime.now();

    BigDecimal fee = ParkingUtils.calculateCost(clientParkingSpace.getEntryDate(), exitDate);

    clientParkingSpace.setFee(fee);

    long parkingTimes = clientParkingSpaceRepository
        .countByClientCpfAndExitDateIsNotNull(clientParkingSpace.getClient().getCpf());

    clientParkingSpace.setDiscount(
        ParkingUtils.calcDiscount(fee, parkingTimes)
    );

    clientParkingSpace.setExitDate(exitDate);
    clientParkingSpace.getParkingSpace().setStatus(ParkingSpaceStatus.AVAILABLE);

    clientParkingSpaceRepository.save(clientParkingSpace);

    return mapperUtils.parseObject(clientParkingSpace, ParkingResponseDTO.class);
  }
}
