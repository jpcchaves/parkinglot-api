package com.jpcchaves.parkinglotapi.service.client;

import com.jpcchaves.parkinglotapi.domain.models.Client;
import com.jpcchaves.parkinglotapi.domain.models.User;
import com.jpcchaves.parkinglotapi.exception.CpfUniqueViolationException;
import com.jpcchaves.parkinglotapi.repository.ClientRepository;
import com.jpcchaves.parkinglotapi.service.user.UserService;
import com.jpcchaves.parkinglotapi.uitls.mapper.MapperUtils;
import com.jpcchaves.parkinglotapi.web.dto.client.ClientCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.client.ClientResponseDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final MapperUtils mapperUtils;
    private final UserService userService;

    public ClientServiceImpl(ClientRepository clientRepository,
                             MapperUtils mapperUtils,
                             UserService userService) {
        this.clientRepository = clientRepository;
        this.mapperUtils = mapperUtils;
        this.userService = userService;
    }

    @Override
    @Transactional
    public ClientResponseDTO create(ClientCreateDTO requestDTO) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userService.getUserById(user.getId());
            Client client = mapperUtils.parseObject(requestDTO, Client.class);

            Client savedClient = clientRepository.save(client);
            return mapperUtils.parseObject(savedClient, ClientResponseDTO.class);
        } catch (DataIntegrityViolationException ex) {
            throw new CpfUniqueViolationException(
                    String.format("CPF %s nao pode ser cadastrado pois ja existe no sistema", requestDTO.getCpf())
            );
        }
    }
}
