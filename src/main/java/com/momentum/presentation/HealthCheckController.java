package com.momentum.presentation;

import com.momentum.application.HealthCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthCheckController {

    private final HealthCheckService healthCheckService;

    @GetMapping("health-check")
    public ResponseEntity<String> healthCheck() {
        String response = healthCheckService.healthCheck();
        return ResponseEntity.ok(response);
    }
}
