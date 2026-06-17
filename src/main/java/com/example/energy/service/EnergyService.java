package com.example.energy.service;

import com.example.energy.model.EnergyUsage;
import com.example.energy.repository.EnergyUsageRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
public class EnergyService {

    private final EnergyUsageRepository repository;
    private static final double ANOMALY_THRESHOLD = 5000.0; // watts

    public EnergyService(EnergyUsageRepository repository) {
        this.repository = repository;
    }

    // Process a single usage entry asynchronously. Marking as @Async makes this method
    // run in a separate thread from the ThreadPoolTaskExecutor defined in AsyncConfig.
    // CompletableFuture is used so callers can wait for or compose results.
    @Async("taskExecutor")
    public CompletableFuture<EnergyUsage> processUsageAsync(EnergyUsage usage) {
        // Simple anomaly detection: thread-safe calculation using local variables only.
        boolean anomaly = isAnomaly(usage.getPowerConsumed());
        usage.setIsAnomaly(anomaly);
        EnergyUsage saved = repository.save(usage); // repository is thread-safe (Spring-managed)
        return CompletableFuture.completedFuture(saved);
    }

    private boolean isAnomaly(Double power) {
        return Objects.nonNull(power) && power > ANOMALY_THRESHOLD;
    }

    // Utility to process a batch concurrently and return when all complete.
    public CompletableFuture<List<EnergyUsage>> processBatch(List<EnergyUsage> usages) {
        CompletableFuture<?>[] futures = usages.stream()
                .map(this::processUsageAsync)
                .toArray(CompletableFuture[]::new);

        return CompletableFuture.allOf(futures)
                .thenApply(v -> usages);
    }
}
