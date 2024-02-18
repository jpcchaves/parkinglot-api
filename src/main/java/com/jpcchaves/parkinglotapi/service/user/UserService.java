package com.jpcchaves.parkinglotapi.service.user;

import com.jpcchaves.parkinglotapi.domain.models.User;
import com.jpcchaves.parkinglotapi.web.dto.user.UserCreateDTO;

import java.util.List;

public interface UserService {
    User createUser(UserCreateDTO requestDTO);

    User getUserById(Long userId);

    User updateUserPassword(Long userId,
                            String password);

    List<User> listAllUsers();
}
