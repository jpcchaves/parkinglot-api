package com.jpcchaves.parkinglotapi.domain.models;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "clients_parking_space")
@EntityListeners(AuditingEntityListener.class)
public class ClientParkingSpace extends AuditedEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1085540345038436182L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "receipt_number", unique = true, nullable = false, length = 15)
    private String receipt;

    @Column(nullable = false, length = 12)
    private String licensePlate;

    @Column(nullable = false, length = 45)
    private String vehicleMake;

    @Column(nullable = false, length = 45)
    private String vehicleModel;

    @Column(nullable = false, length = 45)
    private String vehicleColor;

    @Column(nullable = false)
    private LocalDateTime entryDate;

    @Column
    private LocalDateTime exitDate;

    @Column(columnDefinition = "decimal(7,2)")
    private BigDecimal fee;

    @Column(columnDefinition = "decimal(7,2)")
    private BigDecimal discount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vaga", nullable = false)
    private ParkingSpace parkingSpace;

    public ClientParkingSpace() {
    }

    public ClientParkingSpace(LocalDateTime createdAt,
                              LocalDateTime updatedAt,
                              String createdBy,
                              String modifiedBy,
                              Long id,
                              String receipt,
                              String licensePlate,
                              String vehicleMake,
                              String vehicleModel,
                              String vehicleColor,
                              LocalDateTime entryDate,
                              LocalDateTime exitDate,
                              BigDecimal fee,
                              BigDecimal discount,
                              Client client,
                              ParkingSpace parkingSpace) {
        super(createdAt, updatedAt, createdBy, modifiedBy);
        this.id = id;
        this.receipt = receipt;
        this.licensePlate = licensePlate;
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehicleColor = vehicleColor;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.fee = fee;
        this.discount = discount;
        this.client = client;
        this.parkingSpace = parkingSpace;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientParkingSpace that = (ClientParkingSpace) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ClientParkingSpace{" +
                "id=" + id +
                ", receipt='" + receipt + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", vehicleMake='" + vehicleMake + '\'' +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", vehicleColor='" + vehicleColor + '\'' +
                ", entryDate=" + entryDate +
                ", exitDate=" + exitDate +
                ", fee=" + fee +
                ", discount=" + discount +
                ", client=" + client +
                ", parkingSpace=" + parkingSpace +
                '}';
    }
}
