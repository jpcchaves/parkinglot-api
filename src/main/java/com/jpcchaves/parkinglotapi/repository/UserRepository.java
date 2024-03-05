package com.jpcchaves.parkinglotapi.repository;

import com.jpcchaves.parkinglotapi.domain.Enum.Role;
import com.jpcchaves.parkinglotapi.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(
            "select u.role from User u where u.username like :username"
    )
    Role findRoleByUsername(String username);
}