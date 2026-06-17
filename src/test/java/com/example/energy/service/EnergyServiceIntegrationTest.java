package com.example.energy.service;

import com.example.energy.model.EnergyUsage;
import com.example.energy.repository.EnergyUsageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EnergyServiceIntegrationTest {

    @Autowired
    private EnergyService energyService;

    @Autowired
    private EnergyUsageRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void processBatch_flagsAnomaliesAndSavesAll() throws Exception {
        EnergyUsage e1 = new EnergyUsage("dev-1", 1200.0, Instant.now());
        EnergyUsage e2 = new EnergyUsage("dev-2", 6000.0, Instant.now()); // anomaly
        EnergyUsage e3 = new EnergyUsage("dev-3", 450.0, Instant.now());

        List<EnergyUsage> usages = Arrays.asList(e1, e2, e3);

        // Process asynchronously and wait for completion
        energyService.processBatch(usages).get(5, TimeUnit.SECONDS);

        List<EnergyUsage> saved = repository.findAll();
        assertThat(saved).hasSize(3);
        assertThat(saved).anyMatch(u -> u.getDeviceId().equals("dev-2") && Boolean.TRUE.equals(u.getIsAnomaly()));
    }
}
