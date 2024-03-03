package com.jpcchaves.parkinglotapi.service.user;

import com.jpcchaves.parkinglotapi.domain.Enum.Role;
import com.jpcchaves.parkinglotapi.domain.models.User;
import com.jpcchaves.parkinglotapi.web.dto.user.UserCreateDTO;
import com.jpcchaves.parkinglotapi.web.dto.user.UserResponseDTO;
import com.jpcchaves.parkinglotapi.web.dto.user.UserUpdatePasswordDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserCreateDTO requestDTO);

    UserResponseDTO getUserById(Long userId);

    User updateUserPassword(Long userId,
                            UserUpdatePasswordDTO requestDTO);

    List<UserResponseDTO> listAllUsers();

    User getUserByUsername(String username);

    Role findRoleByUsername(String username);
}
