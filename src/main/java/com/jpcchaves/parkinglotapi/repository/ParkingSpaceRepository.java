package com.jpcchaves.parkinglotapi.repository;

import com.jpcchaves.parkinglotapi.domain.models.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {
}
