package com.momentum.domain.service;

import com.momentum.domain.dto.response.GetAllHospitalInfoTotalResponse;
import com.momentum.domain.dto.response.GetHospitalInfoResponse;
import com.momentum.domain.repository.HospitalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HospitalInfoService {

    private final HospitalInfoRepository hospitalInfoRepository;

    public Optional<GetHospitalInfoResponse> getHospitalInfoResponse(Long hospitalId) {
        return hospitalInfoRepository.findById(hospitalId)
                .map(GetHospitalInfoResponse::of);
    }

    /**
     *
     * @param hospital,address : 병원명 또는 지역
     * @return : 병원명 또는 지역에 따른 후기
     */
    public List<GetAllHospitalInfoTotalResponse> getHospitalInfoTotal(final String hospital, final String address) {
        return hospitalInfoRepository.findByHospitalContainingOrAddressContaining(hospital, address)
                .stream()
                .map(GetAllHospitalInfoTotalResponse::of)
                .toList();
    }
}
