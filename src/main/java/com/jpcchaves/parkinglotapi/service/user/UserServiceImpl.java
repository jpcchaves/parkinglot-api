package com.jpcchaves.parkinglotapi.service.user;

import com.jpcchaves.parkinglotapi.domain.Enum.Role;
import com.jpcchaves.parkinglotapi.domain.models.User;
import com.jpcchaves.parkinglotapi.exception.EntityNotFoundException;
import com.jpcchaves.parkinglotapi.exception.PasswordInvalidException;
import com.jpcchaves.parkinglotapi.exception.UniqueConstraintViolationException;
import com.jpcchaves.parkinglotapi.repository.UserRepository;
import com.jpcchaves.parkinglotapi.uitls.mapper.MapperUtils;
import com.jpcchaves.parkinglotapi.web.dto.user.UserCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.user.UserResponseDTO;
import com.jpcchaves.parkinglotapi.web.dto.user.UserUpdatePasswordDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MapperUtils mapperUtils;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           MapperUtils mapperUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapperUtils = mapperUtils;
    }

    @Override
    @Transactional
    public UserResponseDTO createUser(UserCreateDTO requestDTO) {
        try {
        User user = mapperUtils.parseObject(requestDTO, User.class);
        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        User savedUser = userRepository.save(user);
        return mapperUtils.parseObject(savedUser, UserResponseDTO.class);
        } catch (DataIntegrityViolationException ex) {
            throw new UniqueConstraintViolationException("Email %s ja cadastrado".formatted(requestDTO.getUsername()));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(Long userId) {
        User user =  userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("Usuario nao encontrado")
        );

        return mapperUtils.parseObject(user, UserResponseDTO.class);
    }

    @Override
    @Transactional
    public User updateUserPassword(Long userId,
                                   UserUpdatePasswordDTO requestDTO) {
        if(!passwordMatches(requestDTO.getNewPassword(), requestDTO.getConfirmNewPassword())) {
            throw new PasswordInvalidException("A nova senha nao condiz com a confirmacao");
        }

        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("Usuario nao encontrado")
        );

        if(!passwordEncoder.matches(requestDTO.getCurrentPassword(), user.getPassword())) {
            throw new PasswordInvalidException("A senha atual nao condiz com a senha cadastrada pelo usuario");
        }

        user.setPassword(passwordEncoder.encode(requestDTO.getNewPassword()));

        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> listAllUsers() {
        return mapperUtils.parseObjectsCollection(userRepository.findAll(), UserResponseDTO.class);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuario nao encontrado com o username informado: %s".formatted(username)));
    }


    @Override
    public Role findRoleByUsername(String username) {
        return userRepository.findRoleByUsername(username);
    }

    private boolean passwordMatches(String passwordToCheck, String passwordToCheckAgainst) {
        return passwordToCheck.equals(passwordToCheckAgainst);
    }
}
