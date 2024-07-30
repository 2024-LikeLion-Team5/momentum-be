package com.momentum.domain.service;

import com.momentum.domain.IntegrationSearchRepository;
import com.momentum.domain.dto.response.*;
import com.momentum.domain.entity.HospitalInfo;
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

    private final IntegrationSearchRepository integrationSearchRepository;
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
    public GetAllHospitalInfoTotalResponse getHospitalInfoTotal(final String keyword) {
        Pageable pageable = PageRequest.of(0, 3);
        long totalSearchedCount = hospitalInfoRepository.countByHospitalContainingOrAddressContaining(keyword, keyword);

//         무시할 부분
//        List<HospitalInfo> hospitalInfos = hospitalInfoRepository.findByHospitalContainingOrAddressContaining(keyword, keyword, pageable);
//
//        // totalSearchedCount 찾기, 여기 메서드 통합 ?
//        List<IntegrationHospitalSearchDto> integrationHospitalSearchDtos = hospitalInfos.stream()
//                .map(this::convertToIntegrationHospitalSearchDto)
//                .toList();
//         무시한 부분

        List<IntegrationHospitalSearchDto> integrationHospitalSearchDtos = integrationSearchRepository
                .findAllByKeyword(keyword, pageable)
                .map(IntegrationHospitalSearchDto::from)
                .toList();
        return GetAllHospitalInfoTotalResponse.of(totalSearchedCount, integrationHospitalSearchDtos);
    }

    private IntegrationHospitalSearchDto convertToIntegrationHospitalSearchDto(HospitalInfo hospitalInfo) {
        return new IntegrationHospitalSearchDto(
                hospitalInfo.getId(),
                hospitalInfo.getHospital(),
                hospitalInfo.getAddress(),
                hospitalInfo.getAverageFacilityRating(),
                hospitalInfo.getAverageAtmosphereRating(),
                hospitalInfo.getAverageEmployeeRating()
        );
    }
}
