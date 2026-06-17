package com.example.energy.controller;

import com.example.energy.model.EnergyUsage;
import com.example.energy.service.EnergyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/energy")
public class EnergyController {

    private final EnergyService service;

    public EnergyController(EnergyService service) {
        this.service = service;
    }

    // Accepts a list of EnergyUsage objects and processes them concurrently.
    @PostMapping("/usage")
    public ResponseEntity<?> ingestUsages(@Valid @RequestBody List<EnergyUsage> usages) {
        CompletableFuture<List<EnergyUsage>> result = service.processBatch(usages);
        // Return 202 Accepted to indicate processing is happening asynchronously.
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Processing " + usages.size() + " records");
    }
}
