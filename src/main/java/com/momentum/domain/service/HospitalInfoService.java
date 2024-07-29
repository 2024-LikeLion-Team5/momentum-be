package com.momentum.domain.service;

import com.momentum.domain.dto.response.GetAllHospitalInfoTotalResponse;
import com.momentum.domain.dto.response.GetHospitalInfoResponse;
import com.momentum.domain.repository.HospitalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
     * @param keyword : 병원명 또는 지역
     * @return : 병원명 또는 지역에 따른 후기
     */
    public List<GetAllHospitalInfoTotalResponse> getHospitalInfoTotal(final String keyword) {
        Pageable pageable = PageRequest.of(0, 3);
        return hospitalInfoRepository.findByHospitalContainingOrAddressContaining(keyword, keyword, pageable)
                .stream()
                .map(GetAllHospitalInfoTotalResponse::of)
                .toList();
    }
}
