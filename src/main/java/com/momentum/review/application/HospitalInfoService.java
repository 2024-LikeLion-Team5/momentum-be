package com.momentum.review.application;

import com.momentum.community.domain.IntegrationSearchRepository;
import com.momentum.review.domain.HospitalInfoRepository;
import com.momentum.review.dto.response.GetAllHospitalInfoTotalResponse;
import com.momentum.review.dto.response.GetHospitalInfoResponse;
import com.momentum.review.dto.response.IntegrationHospitalSearchDto;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HospitalInfoService {

    private final IntegrationSearchRepository integrationSearchRepository;
    private final HospitalInfoRepository hospitalInfoRepository;

    public Optional<GetHospitalInfoResponse> getHospitalInfoResponse(Long hospitalId) {
        return hospitalInfoRepository.findById(hospitalId)
                .map(GetHospitalInfoResponse::of);
    }

    public GetAllHospitalInfoTotalResponse getHospitalInfoTotal(final String keyword) {
        Pageable pageable = PageRequest.of(0, 3);
        long totalSearchedCount = hospitalInfoRepository.countByHospitalContainingOrAddressContaining(
                keyword, keyword);
        List<IntegrationHospitalSearchDto> integrationHospitalSearchDtos =
                hospitalInfoRepository.findByHospitalContainingOrAddressContaining(keyword, keyword, pageable)
                        .stream()
                        .map(IntegrationHospitalSearchDto::from)
                        .toList();
        return GetAllHospitalInfoTotalResponse.of(totalSearchedCount, integrationHospitalSearchDtos);
    }
}
