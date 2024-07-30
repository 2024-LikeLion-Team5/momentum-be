package com.momentum.domain.service;

import com.momentum.domain.dto.request.CreateDoctorTreatmentReviewPostRequest;
import com.momentum.domain.dto.response.*;
import com.momentum.domain.entity.DoctorTreatmentReviewPost;
import com.momentum.domain.repository.DoctorTreatmentReviewPostRepository;
import com.momentum.dto.response.community.GetCommunityPostTotalResponse;
import com.momentum.dto.response.community.IntegrationCommunitySearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

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
                .orElseThrow();
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
