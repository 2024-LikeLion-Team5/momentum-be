package com.momentum.review.application;

import com.momentum.community.exception.CommunityPostException;
import com.momentum.global.exception.NotFoundException;
import com.momentum.review.domain.HospitalInfo;
import com.momentum.review.domain.HospitalInfoRepository;
import com.momentum.review.domain.HospitalReviewPost;
import com.momentum.review.domain.HospitalReviewPostRepository;
import com.momentum.review.dto.request.CreateHospitalReviewPostRequest;
import com.momentum.review.dto.response.GetHospitalReviewPostResponse;
import com.momentum.review.dto.response.GetHospitalReviewPostTotalResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HospitalReviewPostService {

    private static final int INITIAL_PAGE_SIZE = 10;

    private final HospitalReviewPostRepository hospitalReviewPostRepository;
    private final HospitalInfoRepository hospitalInfoRepository;

    @Transactional
    public Long createHospitalReviewPost(CreateHospitalReviewPostRequest request) {
        // 이 부분 병원 정보로 해당 리뷰 글 찾는 게 맞나 ?
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

        // 해당 리뷰 글 찾았으면 제대로 업데이트
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
//        hospitalInfo.setTotalReviews(reviewCount);

        hospitalInfoRepository.save(hospitalInfo);
    }

    public GetHospitalReviewPostResponse getHospitalReviewPost(Long postId) {
        HospitalReviewPost hospitalReviewPost = hospitalReviewPostRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(CommunityPostException.NON_EXISTENT_HOSPITAL_REVIEW_POST));
        hospitalReviewPost.increaseHits();
        return GetHospitalReviewPostResponse.of(hospitalReviewPost);
    }

    public List<GetHospitalReviewPostResponse> getAllHospitalReviewPosts(
            final int page,
            final Long hospitalId
    ) {
        // 2024-07-31 목록 조회라 page 사용하고 병원 id로 병원 찾는 로직 수정 완
        Pageable pageable = PageRequest.of(page, INITIAL_PAGE_SIZE);
        return hospitalReviewPostRepository.findById(hospitalId, pageable)
                .stream()
                .map(GetHospitalReviewPostResponse::of)
                .toList();
    }

    public List<GetHospitalReviewPostTotalResponse> getHospitalReviewPostsTotal() {
        return hospitalReviewPostRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(GetHospitalReviewPostTotalResponse::of)
                .toList();
    }
}
