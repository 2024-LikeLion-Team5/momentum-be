package com.momentum.review.application;

import com.momentum.global.exception.NotFoundException;
import com.momentum.review.domain.HospitalInfo;
import com.momentum.review.domain.HospitalInfoRepository;
import com.momentum.review.dto.response.GetAllHospitalInfoTotalResponse;
import com.momentum.review.dto.response.GetHospitalInfoResponse;
import com.momentum.review.dto.response.IntegrationHospitalSearchDto;
import com.momentum.review.exception.HospitalInfoException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HospitalInfoService {

    private final HospitalInfoRepository hospitalInfoRepository;

    public GetHospitalInfoResponse getHospitalInfoResponse(Long hospitalId) {
        HospitalInfo hospitalInfo = hospitalInfoRepository.findById(hospitalId)
                .orElseThrow(() -> new NotFoundException(HospitalInfoException.NON_EXISTENT_HOSPITAL_INFORMATION));
        return GetHospitalInfoResponse.from(hospitalInfo);
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
