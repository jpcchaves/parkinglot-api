package com.jpcchaves.parkinglotapi.repository;

import com.jpcchaves.parkinglotapi.domain.models.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {
    Optional<ParkingSpace> findByParkingSpaceCode(String code);
}
