package com.jpcchaves.parkinglotapi.repository;

import com.jpcchaves.parkinglotapi.domain.models.ClientParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientParkingSpaceRepository extends JpaRepository<ClientParkingSpace, Long> {
  Optional<ClientParkingSpace> findByReceiptAndExitDateIsNull(String receipt);

  long countByClientCpfAndExitDateIsNotNull(String cpf);
}
