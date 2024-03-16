package com.jpcchaves.parkinglotapi.service.parkingspace;

import com.jpcchaves.parkinglotapi.domain.models.ParkingSpace;
import com.jpcchaves.parkinglotapi.exception.EntityNotFoundException;
import com.jpcchaves.parkinglotapi.exception.ParkingSpaceCodeUniqueViolationException;
import com.jpcchaves.parkinglotapi.repository.ParkingSpaceRepository;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingSpaceCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.ParkingSpaceRespondeDTO;
import com.jpcchaves.parkinglotapi.web.dto.parkingspace.mapper.ParkingSpaceMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParkingSpaceServiceImpl implements ParkingSpaceService {
    private final ParkingSpaceRepository parkingSpaceRepository;

    private final ParkingSpaceMapper parkingSpaceMapper;

    public ParkingSpaceServiceImpl(ParkingSpaceRepository parkingSpaceRepository,
                                   ParkingSpaceMapper parkingSpaceMapper) {
        this.parkingSpaceRepository = parkingSpaceRepository;
        this.parkingSpaceMapper = parkingSpaceMapper;
    }

    @Override
    @Transactional
    public ParkingSpaceRespondeDTO create(ParkingSpaceCreateDTO requestDTO) {
        try {
            ParkingSpace parkingSpace = parkingSpaceRepository
                    .save(parkingSpaceMapper.toParkingSpace(requestDTO));
            return parkingSpaceMapper.toParkingSpaceResponseDTO(parkingSpace);
        } catch (DataIntegrityViolationException ex) {
            throw new ParkingSpaceCodeUniqueViolationException("O codigo da vaga informado ja existe %s"
                    .formatted(requestDTO.getParkingSpaceCode()));
        }
    }

    private ParkingSpace findByCode(String code) {
        return parkingSpaceRepository
                .findByParkingSpaceCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Vaga nao encontrada com o codigo informado"));
    }
}
