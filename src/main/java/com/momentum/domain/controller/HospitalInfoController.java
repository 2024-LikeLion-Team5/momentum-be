package com.momentum.domain.controller;

import com.momentum.domain.dto.response.GetAllHospitalInfoTotalResponse;
import com.momentum.domain.dto.response.GetHospitalInfoResponse;
import com.momentum.domain.service.HospitalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/hospital-search/hospitals")
    public ResponseEntity<List<GetAllHospitalInfoTotalResponse>> getHospitalInfoTotal(
            @RequestParam(required = false) String hospital,
            @RequestParam(required = false) String address
    ) {
        List<GetAllHospitalInfoTotalResponse> responses = hospitalInfoService.getHospitalInfoTotal(hospital, address);
        return ResponseEntity.ok(responses);
    }
}
