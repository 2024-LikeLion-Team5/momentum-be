package com.momentum.application;

import com.momentum.domain.IntegrationSearchRepository;
import com.momentum.domain.dto.response.*;
import com.momentum.domain.entity.DoctorTreatmentReviewPost;
import com.momentum.domain.entity.HospitalReviewPost;
import com.momentum.domain.repository.HospitalInfoRepository;
import com.momentum.domain.service.HospitalInfoService;
import com.momentum.dto.response.community.GetCommunityIntegrationSearchResponse;
import com.momentum.dto.response.community.GetCommunityPostTotalResponse;
import com.momentum.dto.response.community.IntegrationCommunitySearchDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IntegrationSearchService {

    private static final int INITIAL_PAGE_SIZE = 10;

    private final IntegrationSearchRepository integrationSearchRepository;
    private final HospitalInfoRepository hospitalInfoRepository;

    public List<GetCommunityIntegrationSearchResponse> getCommunityPosts(final String keyword, final int page) {
        Pageable pageable = PageRequest.of(page, INITIAL_PAGE_SIZE);
        return integrationSearchRepository.findAllByKeyword(keyword, pageable)
                .stream()
                .map(IntegrationCommunitySearchDto::from)
                .map(GetCommunityIntegrationSearchResponse::from)
                .toList();
    }

    public GetCommunityPostTotalResponse getCommunityPostsTotal(final String keyword) {
        Pageable pageable = PageRequest.of(0, 6);
        long totalSearchedCount = integrationSearchRepository.countAllByKeyword(keyword);
        List<IntegrationCommunitySearchDto> integrationCommunitySearchDtos =
                integrationSearchRepository.findAllByKeyword(keyword, pageable)
                        .stream()
                        .map(IntegrationCommunitySearchDto::from)
                        .toList();
        return GetCommunityPostTotalResponse.of(totalSearchedCount, integrationCommunitySearchDtos);
    }

    // 2024-07-31 기준 수정 완
    // TODO: countAllByKeyword 메서드의 쿼리를 부탁드리겠습니다
    public GetDoctorTreatmentReviewPostTotalResponse getDoctorTreatmentReviewPostsTotal(final String keyword) {
        Pageable pageable = PageRequest.of(0, 3);
        long totalSearchedCount = integrationSearchRepository.countAllByKeyword(keyword);
        List<IntegrationDoctorReviewSearchDto> integrationDoctorReviewSearchDtos =
                integrationSearchRepository.findAllByKeyword(keyword, pageable)
                        .stream()
                        .map(IntegrationDoctorReviewSearchDto::from)
                        .toList();
        return GetDoctorTreatmentReviewPostTotalResponse.of(totalSearchedCount, integrationDoctorReviewSearchDtos);
    }

    public List<IntegrationDoctorReviewSearchDto> getDoctorReviewPosts(final String keyword, final int page) {
        Pageable pageable = PageRequest.of(page, INITIAL_PAGE_SIZE);
        return integrationSearchRepository.findAllByKeyword(keyword, pageable)
                .stream()
                .map(IntegrationDoctorReviewSearchDto::from)
                .toList();
    }

    public GetAllHospitalInfoTotalResponse getHospitalReviewPostTotal(final String keyword) {
        Pageable pageable = PageRequest.of(0, 3);
        long totalSearchedCount = integrationSearchRepository.countAllByKeyword(keyword);
        List<IntegrationHospitalSearchDto> integrationHospitalSearchDtos =
                integrationSearchRepository.findAllByKeyword(keyword, pageable)
                        .stream()
                        .map(IntegrationHospitalSearchDto::from)
                        .toList();
        return GetAllHospitalInfoTotalResponse.of(totalSearchedCount, integrationHospitalSearchDtos);
    }

    public GetAllHospitalInfoTotalResponse getHospitaInfos(final String keyword, final int page) {
        Pageable pageable = PageRequest.of(page, INITIAL_PAGE_SIZE);

        long totalSearchedCount = hospitalInfoRepository.countByHospitalContainingOrAddressContaining(keyword, keyword);

        List<IntegrationHospitalSearchDto> integrationHospitalSearchDtos = integrationSearchRepository
                .findAllByKeyword(keyword, pageable)
                .map(IntegrationHospitalSearchDto::from)
                .toList();
        return GetAllHospitalInfoTotalResponse.of(totalSearchedCount, integrationHospitalSearchDtos);
    }
}
