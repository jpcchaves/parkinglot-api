package com.jpcchaves.parkinglotapi.repository;

import com.jpcchaves.parkinglotapi.domain.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
