package com.jpcchaves.parkinglotapi.repository;

import com.jpcchaves.parkinglotapi.domain.models.ClientParkingSpace;
import com.jpcchaves.parkinglotapi.repository.projection.ClientParkingProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientParkingSpaceRepository extends JpaRepository<ClientParkingSpace, Long> {
  Optional<ClientParkingSpace> findByReceiptAndExitDateIsNull(String receipt);

  long countByClientCpfAndExitDateIsNotNull(String cpf);

  Page<ClientParkingProjection> findAllByClientCpf(String cpf,
                                                   Pageable pageable);
}
