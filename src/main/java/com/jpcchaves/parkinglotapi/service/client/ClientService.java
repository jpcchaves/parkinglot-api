package com.jpcchaves.parkinglotapi.service.client;

import com.jpcchaves.parkinglotapi.web.dto.client.ClientCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.client.ClientResponseDTO;

public interface ClientService {
    ClientResponseDTO create(ClientCreateDTO requestDTO);
}
