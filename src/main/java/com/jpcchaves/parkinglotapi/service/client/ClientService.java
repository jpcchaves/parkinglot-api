package com.jpcchaves.parkinglotapi.service.client;

import com.jpcchaves.parkinglotapi.domain.models.Client;
import com.jpcchaves.parkinglotapi.web.dto.client.ClientCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.client.ClientResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {
    ClientResponseDTO create(ClientCreateDTO requestDTO);

    ClientResponseDTO getById(Long clientId);
    Page<Client> getClientsList(Pageable pageable);
}
