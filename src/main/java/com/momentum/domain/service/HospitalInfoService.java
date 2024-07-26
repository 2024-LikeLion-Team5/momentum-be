package com.momentum.domain.service;

import com.momentum.domain.dto.response.GetHospitalInfoResponse;
import com.momentum.domain.repository.HospitalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HospitalInfoService {

    private final HospitalInfoRepository hospitalInfoRepository;

    public Optional<GetHospitalInfoResponse> getHospitalInfoResponse(Long hospitalId) {
        return hospitalInfoRepository.findById(hospitalId)
                .map(GetHospitalInfoResponse::of);
    }
}
