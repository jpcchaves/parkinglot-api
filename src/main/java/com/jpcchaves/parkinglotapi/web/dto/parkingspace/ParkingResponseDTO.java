package com.jpcchaves.parkinglotapi.web.dto.parkingspace;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ParkingResponseDTO {
    private String licensePlate;
    private String vehicleModel;
    private String vehicleMake;
    private String vehicleColor;
    private String clientCpf;
    private String receipt;
    private LocalDate entryDate;
    private LocalDateTime exitDate;
    private String parkingSpaceCode;
    private BigDecimal fee;
    private BigDecimal discount;

    public ParkingResponseDTO() {
    }

    public ParkingResponseDTO(String licensePlate,
                              String vehicleModel,
                              String vehicleMake,
                              String vehicleColor,
                              String clientCpf,
                              String receipt,
                              LocalDate entryDate,
                              LocalDateTime exitDate,
                              String parkingSpaceCode,
                              BigDecimal fee,
                              BigDecimal discount) {
        this.licensePlate = licensePlate;
        this.vehicleModel = vehicleModel;
        this.vehicleMake = vehicleMake;
        this.vehicleColor = vehicleColor;
        this.clientCpf = clientCpf;
        this.receipt = receipt;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.parkingSpaceCode = parkingSpaceCode;
        this.fee = fee;
        this.discount = discount;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public String getClientCpf() {
        return clientCpf;
    }

    public void setClientCpf(String clientCpf) {
        this.clientCpf = clientCpf;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDateTime getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDateTime exitDate) {
        this.exitDate = exitDate;
    }

    public String getParkingSpaceCode() {
        return parkingSpaceCode;
    }

    public void setParkingSpaceCode(String parkingSpaceCode) {
        this.parkingSpaceCode = parkingSpaceCode;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
