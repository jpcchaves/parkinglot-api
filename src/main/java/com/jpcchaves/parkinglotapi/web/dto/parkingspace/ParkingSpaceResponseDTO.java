package com.jpcchaves.parkinglotapi.web.dto.parkingspace;

public class ParkingSpaceResponseDTO {
    private Long id;
    private String parkingSpaceCode;
    private String status;

    public ParkingSpaceResponseDTO() {
    }

    public ParkingSpaceResponseDTO(Long id,
                                   String parkingSpaceCode,
                                   String status) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
