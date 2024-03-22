package com.jpcchaves.parkinglotapi.repository;

import com.jpcchaves.parkinglotapi.domain.models.ClientParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientParkingSpaceRepository extends JpaRepository<ClientParkingSpace, Long> {
}
