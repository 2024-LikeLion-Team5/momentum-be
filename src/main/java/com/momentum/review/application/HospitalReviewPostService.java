package com.momentum.review.application;

import com.momentum.community.exception.CommunityPostException;
import com.momentum.global.exception.NotFoundException;
import com.momentum.review.domain.DoctorRepository;
import com.momentum.review.domain.HospitalInfo;
import com.momentum.review.domain.HospitalInfoRepository;
import com.momentum.review.domain.HospitalReviewPost;
import com.momentum.review.domain.HospitalReviewPostRepository;
import com.momentum.review.dto.request.CreateHospitalReviewPostRequest;
import com.momentum.review.dto.response.GetDoctorResponse;
import com.momentum.review.dto.response.GetHospitalReviewPostResponse;
import com.momentum.review.dto.response.GetHospitalReviewPostTotalResponse;
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
public class HospitalReviewPostService {

    private static final int INITIAL_PAGE_SIZE = 10;

    private final HospitalReviewPostRepository hospitalReviewPostRepository;
    private final HospitalInfoRepository hospitalInfoRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    public Long createHospitalReviewPost(CreateHospitalReviewPostRequest request) {
        HospitalInfo hospitalInfo = hospitalInfoRepository.findByHospital(request.hospital())
                .orElseThrow(() -> new NotFoundException(HospitalInfoException.NON_EXISTENT_HOSPITAL_INFORMATION));
        HospitalReviewPost hospitalReviewPost = HospitalReviewPost.builder()
                .hospitalInfo(hospitalInfo)
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
        return hospitalReviewPostRepository.findAllByHospitalInfoId(hospitalId, pageable)
                .stream()
                .map(GetHospitalReviewPostResponse::of)
                .toList();
    }

    public List<GetHospitalReviewPostTotalResponse> getHospitalReviewPostsTotal() {
        return hospitalReviewPostRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(GetHospitalReviewPostTotalResponse::from)
                .toList();
    }

    public List<GetDoctorResponse> getDoctors(Long hospitalId) {
        HospitalInfo hospitalInfo = hospitalInfoRepository.findById(hospitalId)
                .orElseThrow(() -> new NotFoundException(HospitalInfoException.NON_EXISTENT_HOSPITAL_INFORMATION));
        return doctorRepository.findAllByHospitalInfo(hospitalInfo)
                .stream()
                .map(GetDoctorResponse::from)
                .toList();
    }
}
