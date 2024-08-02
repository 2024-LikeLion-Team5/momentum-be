package com.momentum.community.application;

import com.momentum.community.domain.IntegrationSearchRepository;
import com.momentum.community.dto.response.GetCommunityIntegrationSearchResponse;
import com.momentum.community.dto.response.GetCommunityPostTotalResponse;
import com.momentum.community.dto.response.IntegrationCommunitySearchDto;
import com.momentum.review.domain.DoctorTreatmentReviewPost;
import com.momentum.review.domain.DoctorTreatmentReviewPostRepository;
import com.momentum.review.domain.HospitalInfoRepository;
import com.momentum.review.dto.response.GetAllHospitalInfoTotalResponse;
import com.momentum.review.dto.response.GetDoctorTreatmentReviewPostTotalResponse;
import com.momentum.review.dto.response.IntegrationDoctorReviewSearchDto;
import com.momentum.review.dto.response.IntegrationHospitalSearchDto;
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
    private final DoctorTreatmentReviewPostRepository doctorTreatmentReviewPostRepository;

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

    public GetDoctorTreatmentReviewPostTotalResponse getDoctorTreatmentReviewPostsTotal(final String keyword) {
        long totalSearchedCount =
                doctorTreatmentReviewPostRepository.countAllByTitleAndContentContainingOrderByCreatedAtDesc(keyword);

        List<IntegrationDoctorReviewSearchDto> integrationDoctorReviewResponseSearchDtos;
        if (keyword == null) {
            List<DoctorTreatmentReviewPost> doctorTreatmentReviewPosts = doctorTreatmentReviewPostRepository.findAllByOrderByCreatedAt();
            return GetDoctorTreatmentReviewPostTotalResponse.of(
                    totalSearchedCount,
                    doctorTreatmentReviewPosts
            );
        }

        List<DoctorTreatmentReviewPost> doctorTreatmentReviewPosts = doctorTreatmentReviewPostRepository.findAllByTitleContainingOrContentContainingOrderByCreatedAtDesc(
                        keyword,
                        keyword
                ).stream()
                .limit(3)
                .toList();
        return GetDoctorTreatmentReviewPostTotalResponse.of(
                totalSearchedCount,
                doctorTreatmentReviewPosts
        );
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

        long totalSearchedCount =
                hospitalInfoRepository.countByHospitalContainingOrAddressContaining(keyword, keyword);

        List<IntegrationHospitalSearchDto> integrationHospitalSearchDtos =
                hospitalInfoRepository.findByHospitalContainingOrAddressContaining(keyword, keyword, pageable)
                        .stream()
                        .map(IntegrationHospitalSearchDto::from)
                        .toList();
        return GetAllHospitalInfoTotalResponse.of(totalSearchedCount, integrationHospitalSearchDtos);
    }

    public GetAllHospitalInfoTotalResponse getHospitalInfos(final String keyword, final int page) {
        Pageable pageable = PageRequest.of(page, INITIAL_PAGE_SIZE);

        long totalSearchedCount =
                hospitalInfoRepository.countByHospitalContainingOrAddressContaining(keyword, keyword);

        List<IntegrationHospitalSearchDto> integrationHospitalSearchDtos =
                hospitalInfoRepository.findByHospitalContainingOrAddressContaining(keyword, keyword, pageable)
                        .map(IntegrationHospitalSearchDto::from)
                        .toList();
        return GetAllHospitalInfoTotalResponse.of(totalSearchedCount, integrationHospitalSearchDtos);
    }
}
