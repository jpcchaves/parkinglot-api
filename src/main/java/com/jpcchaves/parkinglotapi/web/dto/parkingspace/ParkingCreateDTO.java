package com.jpcchaves.parkinglotapi.web.dto.parkingspace;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public class ParkingCreateDTO {
    @NotBlank
    @Size(min = 12, max = 12)
    @Pattern(regexp = "[A-Z]{3}-[0-9]{4}]", message = "A placa do veiculo deve seguir o padrao 'XXX-0000'")

    @NotBlank
    private String licensePlate;

    @NotBlank
    private String vehicleModel;

    @NotBlank
    private String vehicleMake;

    @NotBlank
    private String vehicleColor;

    @NotBlank
    @Size(min = 11, max = 11)
    @CPF
    private String clientCpf;

    public ParkingCreateDTO() {
    }

    public ParkingCreateDTO(String licensePlate,
                            String vehicleModel,
                            String vehicleMake,
                            String vehicleColor,
                            String clientCpf) {
        this.licensePlate = licensePlate;
        this.vehicleModel = vehicleModel;
        this.vehicleMake = vehicleMake;
        this.vehicleColor = vehicleColor;
        this.clientCpf = clientCpf;
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
}
