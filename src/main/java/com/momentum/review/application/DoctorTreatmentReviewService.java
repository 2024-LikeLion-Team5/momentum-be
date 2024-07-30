package com.momentum.review.application;

import com.momentum.review.dto.request.CreateDoctorTreatmentReviewPostRequest;
import com.momentum.review.dto.response.GetAllDoctorTreatmentReviewPostResponse;
import com.momentum.review.dto.response.GetDoctorTreatmentReviewPostResponse;
import com.momentum.review.domain.DoctorTreatmentReviewPost;
import com.momentum.review.domain.DoctorTreatmentReviewPostRepository;
import com.momentum.community.exception.CommunityPostException;
import com.momentum.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorTreatmentReviewService {

    private static final int INITIAL_PAGE_SIZE = 10;

    private final DoctorTreatmentReviewPostRepository doctorTreatmentReviewPostRepository;

    @Transactional
    public Long createDoctorTreatmentReviewPost(CreateDoctorTreatmentReviewPostRequest request) {
        DoctorTreatmentReviewPost doctorTreatmentReviewPost = DoctorTreatmentReviewPost.builder()
                .hospital(request.hospital())
                .disease(request.disease())
                .treatment(request.treatment())
                .ageGroup(request.ageGroup())
                .doctor(request.doctor())
                .rating(request.rating())
                .title(request.title())
                .content(request.content())
                .hits(0)
                .likes(0)
                .dislikes(0)
                .build();
        return doctorTreatmentReviewPostRepository.save(doctorTreatmentReviewPost).getId();
    }

    @Transactional
    public GetDoctorTreatmentReviewPostResponse getDoctorTreatmentReviewPost(Long postId) {
        DoctorTreatmentReviewPost doctorTreatmentReviewPost = doctorTreatmentReviewPostRepository.findById(postId)
                .orElseThrow(
                        () -> new NotFoundException(CommunityPostException.NON_EXISTENT_DOCTOR_TREATMENT_REVIEW_POST));
        doctorTreatmentReviewPost.increaseHits();
        return GetDoctorTreatmentReviewPostResponse.of(doctorTreatmentReviewPost);
    }

    @Transactional(readOnly = true)
    public List<GetAllDoctorTreatmentReviewPostResponse> getAllDoctorTreatmentReviewPosts(
            final Long hospitalId,
            final Long doctorId
    ) {
        // 목록 조회라 page 사용하고 병원 id로 병원 찾고 해당하는 의사도 의사 id로 찾고 싶은데 조금 어렵습니다 ..
        // 2024-07-31 기준 page 파라미터 삭제했습니다
        // TODO: 정훈 님께 부탁드릴 곳
        return doctorTreatmentReviewPostRepository.findAll()
                .stream()
                .map(GetAllDoctorTreatmentReviewPostResponse::of)
                .toList();
    }

//    @Transactional(readOnly = true)
//    public List<GetDoctorTreatmentReviewPostTotalResponse> GetDoctorTreatmentReviewPostTotal() {
//        return doctorTreatmentReviewPostRepository.findAllByOrderByCreatedAtDesc()
//                .stream()
//                .map(GetDoctorTreatmentReviewPostTotalResponse::of)
//                .toList();
//    }
}
