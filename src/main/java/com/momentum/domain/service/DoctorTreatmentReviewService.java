package com.momentum.domain.service;

import com.momentum.domain.dto.request.CreateDoctorTreatmentReviewPostRequest;
import com.momentum.domain.dto.response.GetAllDoctorTreatmentReviewPostResponse;
import com.momentum.domain.dto.response.GetDoctorTreatmentReviewPostResponse;
import com.momentum.domain.dto.response.GetDoctorTreatmentReviewPostTotalResponse;
import com.momentum.domain.entity.DoctorTreatmentReviewPost;
import com.momentum.domain.repository.DoctorTreatmentReviewPostRepository;
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

    // 이걸 보고 나머지도 하자 !!
    public GetDoctorTreatmentReviewPostResponse getDoctorTreatmentReviewPost(Long postId) {
        DoctorTreatmentReviewPost doctorTreatmentReviewPost = doctorTreatmentReviewPostRepository.findById(postId)
                .orElseThrow();
        doctorTreatmentReviewPost.increaseHits();
        return GetDoctorTreatmentReviewPostResponse.of(doctorTreatmentReviewPost);
    }

    @Transactional(readOnly = true)
    public List<GetAllDoctorTreatmentReviewPostResponse> getAllDoctorTreatmentReviewPosts(
            final int page,
            final Long hospitalId,
            final Long doctorId
    ) {
        // 목록 조회라 page 사용하고 병원 id로 병원 찾고 해당하는 의사도 의사 id로 찾고 싶은데 조금 어렵습니다 ..
        return doctorTreatmentReviewPostRepository.findAll()
                .stream()
                .map(GetAllDoctorTreatmentReviewPostResponse::of)
                .toList();
    }

    /**
     *
     * @param keyword : 병원명 또는 지역
     * @return : 병원명 또는 지역에 따른 후기
     */
    public List<GetDoctorTreatmentReviewPostTotalResponse> getDoctorTreatmentReviewPosts(
            final String hospital,
            final String doctor
    ) {
        // Pageable pageable = PageRequest.of(page, size);
        return doctorTreatmentReviewPostRepository
                .findByHospitalContainingOrDoctorContainingOrderByCreatedAtDesc(hospital, doctor)
                .stream()
                .map(GetDoctorTreatmentReviewPostTotalResponse::of)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<GetDoctorTreatmentReviewPostTotalResponse> getDoctorTreatmentReviewPostsTotal() {
        Pageable pageable = PageRequest.of(0, 6);
        return doctorTreatmentReviewPostRepository.findAllByOrderByCreatedAtDesc(pageable)
                .stream()
                .map(GetDoctorTreatmentReviewPostTotalResponse::of)
                .toList();
    }
}
