package com.example.energy.repository;

import com.example.energy.model.EnergyUsage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnergyUsageRepository extends JpaRepository<EnergyUsage, Long> {
}
