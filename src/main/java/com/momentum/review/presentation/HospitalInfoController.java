package com.momentum.review.presentation;

import com.momentum.review.dto.response.GetAllHospitalInfoTotalResponse;
import com.momentum.review.dto.response.GetHospitalInfoResponse;
import com.momentum.review.application.HospitalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HospitalInfoController {

    private final HospitalInfoService hospitalInfoService;

    @GetMapping("/hospital-reviews/{hospitalId}")
    public ResponseEntity<GetHospitalInfoResponse> getHospitalInfo(@PathVariable Long hospitalId) {
        return hospitalInfoService.getHospitalInfoResponse(hospitalId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/hospital-search/hospitals")
    public ResponseEntity<GetAllHospitalInfoTotalResponse> getHospitalInfoTotal(
            @RequestParam(required = false) String keyword
    ) {
        GetAllHospitalInfoTotalResponse response = hospitalInfoService.getHospitalInfoTotal(keyword);
        return ResponseEntity.ok(response);
    }
}
