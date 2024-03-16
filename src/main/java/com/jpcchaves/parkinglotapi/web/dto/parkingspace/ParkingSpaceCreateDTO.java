package com.jpcchaves.parkinglotapi.web.dto.parkingspace;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ParkingSpaceCreateDTO {
    @NotBlank
    @Size(min = 4, max = 4)
    private String parkingSpaceCode;

    @NotBlank
    @Pattern(regexp = "AVAILABLE|OCCUPIED")
    private String status;

    public ParkingSpaceCreateDTO() {
    }

    public ParkingSpaceCreateDTO(String parkingSpaceCode,
                                 String status) {
        this.parkingSpaceCode = parkingSpaceCode;
        this.status = status;
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
