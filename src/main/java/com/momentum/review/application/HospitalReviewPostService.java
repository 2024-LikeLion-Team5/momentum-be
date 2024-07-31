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

        // 평점 업데이트
        HospitalInfo hospitalInfo = hospitalInfoRepository.findByHospital(request.hospital());
        long reviewCounts = hospitalReviewPostRepository.countAllByHospitalInfo(hospitalInfo);
        hospitalInfo.updateRatings(
                reviewCounts,
                request.facilityRating(),
                request.atmosphereRating(),
                request.employeeRating()
        );
        return hospitalReviewPostRepository.save(hospitalReviewPost).getId();
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
