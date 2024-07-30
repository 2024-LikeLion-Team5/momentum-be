package com.momentum.healthcheck;

import org.springframework.stereotype.Service;

@Service
public class HealthCheckService {

    public String healthCheck() {
        return "health-check";
    }
}
