//package com.momentum.domain.service;
//
//import com.momentum.domain.dto.response.*;
//import com.momentum.domain.entity.DoctorTreatmentReviewPost;
//import com.momentum.domain.entity.HospitalReviewPost;
//import com.momentum.domain.repository.IntegrationSearchRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@Transactional(readOnly = true)
//@RequiredArgsConstructor
//public class IntegrationSearchService {
//
//    private static final int INITIAL_PAGE_SIZE = 10;
//
//    private final IntegrationSearchRepository integrationSearchRepository;
//
//    public GetDoctorTreatmentReviewPostTotalResponse getDoctorTreatmentReviewPostsTotal(final String keyword) {
//        Pageable pageable = PageRequest.of(0, 3);
//        long totalSearchedCount = integrationSearchRepository.countAllByKeyword(keyword);
//        List<IntegrationDoctorReviewSearchDto> integrationDoctorReviewSearchDtos =
//                integrationSearchRepository.findAllByKeyword(keyword, pageable)
//                        .stream()
//                        .map(IntegrationDoctorReviewSearchDto::from)
//                        .toList();
//        return GetDoctorTreatmentReviewPostTotalResponse.of(totalSearchedCount, (DoctorTreatmentReviewPost) integrationDoctorReviewSearchDtos);
//    }
//
//    public List<GetDoctorReviewIntegrationSearchResponse> getDoctorReviewPosts(final String keyword, final int page) {
//        Pageable pageable = PageRequest.of(page, INITIAL_PAGE_SIZE);
//        return integrationSearchRepository.findAllByKeyword(keyword, pageable)
//                .stream()
//                .map(IntegrationDoctorReviewSearchDto::from)
//                .map(GetDoctorReviewIntegrationSearchResponse::from)
//                .toList();
//    }
//
//    public GetHospitalReviewPostTotalResponse getHospitalReviewPostTotal(final String keyword) {
//        Pageable pageable = PageRequest.of(0, 3);
//        long totalSearchedCount = integrationSearchRepository.countAllByKeyword(keyword);
//        List<IntegrationDoctorReviewSearchDto> integrationDoctorReviewSearchDtos =
//                integrationSearchRepository.findAllByKeyword(keyword, pageable)
//                        .stream()
//                        .map(IntegrationDoctorReviewSearchDto::from)
//                        .toList();
//        return GetHospitalReviewPostTotalResponse.of(totalSearchedCount, (HospitalReviewPost) integrationDoctorReviewSearchDtos);
//    }
//
//    public List<GetHospitalIntegrationSearchResponse> getHospitalPosts(final String keyword, final int page) {
//        Pageable pageable = PageRequest.of(page, INITIAL_PAGE_SIZE);
//        return integrationSearchRepository.findAllByKeyword(keyword, pageable)
//                .stream()
//                .map(IntegrationDoctorReviewSearchDto::from)
//                .map(GetHospitalIntegrationSearchResponse::from)
//                .toList();
//    }
//}
