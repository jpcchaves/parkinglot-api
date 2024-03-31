package com.jpcchaves.parkinglotapi.web.dto.parkingspace;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParkingResponseDTO {
  private String licensePlate;
  private String vehicleModel;
  private String vehicleMake;
  private String vehicleColor;
  private String clientCpf;
  private String receipt;
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime entryDate;
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
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
                            LocalDateTime entryDate,
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

  public LocalDateTime getEntryDate() {
    return entryDate;
  }

  public void setEntryDate(LocalDateTime entryDate) {
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
