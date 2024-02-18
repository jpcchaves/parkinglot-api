package com.jpcchaves.parkinglotapi.service.user;

import com.jpcchaves.parkinglotapi.domain.models.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User getUserById(Long userId);

    User updateUserPassword(Long userId,
                            String password);

    List<User> listAllUsers();
}
