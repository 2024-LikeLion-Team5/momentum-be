package com.momentum.review.presentation;

import com.momentum.review.application.HospitalInfoService;
import com.momentum.review.dto.response.GetAllHospitalInfoTotalResponse;
import com.momentum.review.dto.response.GetHospitalInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HospitalInfoController {

    private final HospitalInfoService hospitalInfoService;

    @GetMapping("/hospital-reviews/{hospitalId}")
    public ResponseEntity<GetHospitalInfoResponse> getHospitalInfo(@PathVariable Long hospitalId) {
        GetHospitalInfoResponse response = hospitalInfoService.getHospitalInfoResponse(hospitalId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hospital-search/hospitals")
    public ResponseEntity<GetAllHospitalInfoTotalResponse> getHospitalInfoTotal(
            @RequestParam(required = false) String keyword
    ) {
        GetAllHospitalInfoTotalResponse response = hospitalInfoService.getHospitalInfoTotal(keyword);
        return ResponseEntity.ok(response);
    }
}
