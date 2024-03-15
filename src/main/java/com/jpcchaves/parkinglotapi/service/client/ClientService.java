package com.jpcchaves.parkinglotapi.service.client;

import com.jpcchaves.parkinglotapi.jwt.JwtUserDetails;
import com.jpcchaves.parkinglotapi.web.dto.PageableDTO;
import com.jpcchaves.parkinglotapi.web.dto.client.ClientCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.client.ClientResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ClientService {
    ClientResponseDTO create(ClientCreateDTO requestDTO);

    ClientResponseDTO getById(Long clientId);

    PageableDTO<?> getClientsList(Pageable pageable);

    ClientResponseDTO getClientDetails(JwtUserDetails userDetails);
}
