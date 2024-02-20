package com.jpcchaves.parkinglotapi.service.user;

import com.jpcchaves.parkinglotapi.domain.models.User;
import com.jpcchaves.parkinglotapi.repository.UserRepository;
import com.jpcchaves.parkinglotapi.uitls.mapper.MapperUtils;
import com.jpcchaves.parkinglotapi.web.dto.user.UserCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.user.UserResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MapperUtils mapperUtils;

    public UserServiceImpl(UserRepository userRepository,
                           MapperUtils mapperUtils) {
        this.userRepository = userRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    @Transactional
    public UserResponseDTO createUser(UserCreateDTO requestDTO) {
        User user = mapperUtils.parseObject(requestDTO, User.class);
        User savedUser = userRepository.save(user);
        return mapperUtils.parseObject(savedUser, UserResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("Usuario nao encontrado")
        );
    }

    @Override
    @Transactional
    public User updateUserPassword(Long userId,
                                   String password) {
        User user = getUserById(userId);
        user.setPassword(password);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }
}
