package com.jpcchaves.parkinglotapi.service.client;

import com.jpcchaves.parkinglotapi.domain.models.Client;
import com.jpcchaves.parkinglotapi.domain.models.User;
import com.jpcchaves.parkinglotapi.exception.CpfUniqueViolationException;
import com.jpcchaves.parkinglotapi.exception.EntityNotFoundException;
import com.jpcchaves.parkinglotapi.jwt.JwtUserDetails;
import com.jpcchaves.parkinglotapi.repository.ClientRepository;
import com.jpcchaves.parkinglotapi.repository.UserRepository;
import com.jpcchaves.parkinglotapi.repository.projection.ClientProjection;
import com.jpcchaves.parkinglotapi.uitls.mapper.MapperUtils;
import com.jpcchaves.parkinglotapi.web.dto.PageableDTO;
import com.jpcchaves.parkinglotapi.web.dto.client.ClientCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.client.ClientResponseDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final MapperUtils mapperUtils;
    private final UserRepository userRepository;

    public ClientServiceImpl(ClientRepository clientRepository,
                             MapperUtils mapperUtils,
                             UserRepository userRepository) {
        this.clientRepository = clientRepository;
        this.mapperUtils = mapperUtils;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ClientResponseDTO getById(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client with the given id: %s".formatted(clientId)));
        return mapperUtils.parseObject(client, ClientResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public PageableDTO<?> getClientsList(Pageable pageable) {
        Page<ClientProjection> clients = clientRepository.findAllPageable(pageable);
        return mapperUtils.parseObject(clients, PageableDTO.class);
    }

    @Override
    @Transactional
    public ClientResponseDTO create(ClientCreateDTO requestDTO) {
        try {
            JwtUserDetails jwtUserDetails = (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepository.findById(jwtUserDetails.getId()).orElseThrow(() -> new RuntimeException("An unexpected error occurred. Please, try again!"));
            Client client = mapperUtils.parseObject(requestDTO, Client.class);
            client.setUser(user);
            Client savedClient = clientRepository.save(client);
            return mapperUtils.parseObject(savedClient, ClientResponseDTO.class);
        } catch (DataIntegrityViolationException ex) {
            throw new CpfUniqueViolationException(
                    String.format("CPF %s nao pode ser cadastrado pois ja existe no sistema", requestDTO.getCpf())
            );
        }
    }
}
