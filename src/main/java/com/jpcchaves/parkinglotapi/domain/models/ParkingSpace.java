package com.jpcchaves.parkinglotapi.domain.models;

import com.jpcchaves.parkinglotapi.domain.Enum.ParkingSpaceStatus;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking_spaces")
public class ParkingSpace extends AuditedEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1488968696170747257L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parking_space_code", nullable = false)
    private String parkingSpaceCode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ParkingSpaceStatus status;

    public ParkingSpace() {
    }

    public ParkingSpace(LocalDateTime createdAt,
                        LocalDateTime updatedAt,
                        String createdBy,
                        String modifiedBy,
                        Long id,
                        String parkingSpaceCode,
                        ParkingSpaceStatus status) {
        super(createdAt, updatedAt, createdBy, modifiedBy);
        this.id = id;
        this.parkingSpaceCode = parkingSpaceCode;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParkingSpaceCode() {
        return parkingSpaceCode;
    }

    public void setParkingSpaceCode(String parkingSpaceCode) {
        this.parkingSpaceCode = parkingSpaceCode;
    }

    public ParkingSpaceStatus getStatus() {
        return status;
    }

    public void setStatus(ParkingSpaceStatus status) {
        this.status = status;
    }
}
