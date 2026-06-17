package com.example.energy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name = "energy_usage")
public class EnergyUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String deviceId;

    @NotNull
    private Double powerConsumed; // in watts

    @NotNull
    private Instant timestamp;

    private Boolean isAnomaly = false;

    public EnergyUsage() {}

    public EnergyUsage(String deviceId, Double powerConsumed, Instant timestamp) {
        this.deviceId = deviceId;
        this.powerConsumed = powerConsumed;
        this.timestamp = timestamp;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

    public Double getPowerConsumed() { return powerConsumed; }
    public void setPowerConsumed(Double powerConsumed) { this.powerConsumed = powerConsumed; }

    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }

    public Boolean getIsAnomaly() { return isAnomaly; }
    public void setIsAnomaly(Boolean anomaly) { isAnomaly = anomaly; }
}
