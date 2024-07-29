package com.momentum.domain.service;

import com.momentum.domain.dto.request.CreateHospitalReviewPostRequest;
import com.momentum.domain.dto.response.*;
import com.momentum.domain.entity.HospitalInfo;
import com.momentum.domain.entity.HospitalReviewPost;
import com.momentum.domain.repository.HospitalInfoRepository;
import com.momentum.domain.repository.HospitalReviewPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalReviewPostService {

    private static final int INITIAL_PAGE_SIZE = 10;

    private final HospitalReviewPostRepository hospitalReviewPostRepository;
    private final HospitalInfoRepository hospitalInfoRepository;

    public Long createHospitalReviewPost(CreateHospitalReviewPostRequest request) {
        HospitalInfo hospitalInfo = hospitalInfoRepository.findByHospital(request.hospital());

        HospitalReviewPost hospitalReviewPost = HospitalReviewPost.builder()
                .treatment(request.treatment())
                .hospital(request.hospital())
                .facilityRating(request.facilityRating())
                .atmosphereRating(request.atmosphereRating())
                .employeeRating(request.employeeRating())
                .title(request.title())
                .content(request.content())
                .hits(0)
                .likes(0)
                .dislikes(0)
                .build();

        updateHospitalRatings(hospitalInfo);
        return hospitalReviewPostRepository.save(hospitalReviewPost).getId();
    }

    private void updateHospitalRatings(HospitalInfo hospitalInfo) {
        List<HospitalReviewPost> reviews = hospitalReviewPostRepository.findByHospitalInfo(hospitalInfo);

        double totalFacilityRating = reviews.stream().mapToDouble(HospitalReviewPost::getFacilityRating).sum();
        double totalAtmosphereRating = reviews.stream().mapToDouble(HospitalReviewPost::getAtmosphereRating).sum();
        double totalEmployeeRating = reviews.stream().mapToDouble(HospitalReviewPost::getEmployeeRating).sum();

        int reviewCount = reviews.size();

        hospitalInfo.setAverageFacilityRating(totalFacilityRating / reviewCount);
        hospitalInfo.setAverageAtmosphereRating(totalAtmosphereRating / reviewCount);
        hospitalInfo.setAverageEmployeeRating(totalEmployeeRating / reviewCount);
        hospitalInfo.setTotalReviews(reviewCount);

        hospitalInfoRepository.save(hospitalInfo);
    }

    public GetHospitalReviewPostResponse getHospitalReviewPost(Long postId) {
        HospitalReviewPost hospitalReviewPost = hospitalReviewPostRepository.findById(postId)
                .orElseThrow();
        hospitalReviewPost.increaseHits();
        return GetHospitalReviewPostResponse.of(hospitalReviewPost);
    }

    @Transactional(readOnly = true)
    public List<GetAllHospitalReviewPostResponse> getAllHospitalReviewPosts(
            final int page,
            final Long hospitalId
    ) {
        // 목록 조회라 page 사용하고 병원 id로 병원 찾고 싶은데 조금 어렵습니다 ..
        // 잠시 보류
        Pageable pageable = PageRequest.of(page, INITIAL_PAGE_SIZE);
        return hospitalReviewPostRepository.findAll()
                .stream()
                .map(GetAllHospitalReviewPostResponse::of)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<GetHospitalReviewPostTotalResponse> getHospitalReviewPostsTotal() {
//        Pageable pageable = PageRequest.of(0, 6);
        return hospitalReviewPostRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(GetHospitalReviewPostTotalResponse::of)
                .toList();
    }


}
