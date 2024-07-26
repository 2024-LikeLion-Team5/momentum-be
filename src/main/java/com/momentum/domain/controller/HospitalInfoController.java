package com.momentum.domain.controller;

import com.momentum.domain.dto.response.GetHospitalInfoResponse;
import com.momentum.domain.service.HospitalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hospital-reviews")
public class HospitalInfoController {

    private final HospitalInfoService hospitalInfoService;

    @GetMapping("{hospitalId}")
    public ResponseEntity<GetHospitalInfoResponse> getHospitalInfo(@PathVariable Long hospitalId) {
        return hospitalInfoService.getHospitalInfoResponse(hospitalId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
