package com.jpcchaves.parkinglotapi.repository.projection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface ClientParkingProjection {
  String getvehicleMake();

  String getvehicleColor();

  String getClientCpf();

  String getReceipt();

  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  LocalDateTime getEntryDate();

  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  LocalDateTime getExitDate();

  String getParkingSpaceCode();

  BigDecimal getFee();

  BigDecimal getDiscount();

  String getLicensePlate();

  String getVehicleModel();
}
