package com.momentum.domain.controller;

import com.momentum.application.IntegrationSearchService;
import com.momentum.domain.dto.request.CreateDoctorTreatmentReviewPostRequest;
import com.momentum.domain.dto.response.GetAllDoctorTreatmentReviewPostResponse;
import com.momentum.domain.dto.response.GetDoctorTreatmentReviewPostResponse;
import com.momentum.domain.dto.response.GetDoctorTreatmentReviewPostTotalResponse;
import com.momentum.domain.service.DoctorTreatmentReviewService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DoctorTreatmentReviewController {

    private final IntegrationSearchService integrationSearchService;
    private final DoctorTreatmentReviewService doctorTreatmentReviewService;

    @PostMapping("/hospital-reviews/doctor-reviews")
    public ResponseEntity<Void> createDoctorTreatmentReviewPost(@Valid @RequestBody CreateDoctorTreatmentReviewPostRequest request) {
        Long postId = doctorTreatmentReviewService.createDoctorTreatmentReviewPost(request);
        return ResponseEntity.created(URI.create("/posts/" + postId)).build();
    }

    @GetMapping("/hospital-reviews/doctor-reviews/{postId}")
    public ResponseEntity<GetDoctorTreatmentReviewPostResponse> getDoctorTreatmentReviewPost(@PathVariable Long postId) {
        GetDoctorTreatmentReviewPostResponse responses = doctorTreatmentReviewService.getDoctorTreatmentReviewPost(postId);

        return ResponseEntity.ok(responses);
    }

    // 2024-07-31 push 기준 여전히 문제
    @GetMapping("/hospital-reviews/{hospitalId}/doctor-reviews/{doctorId}")
    public ResponseEntity<List<GetAllDoctorTreatmentReviewPostResponse>> getAllDoctorTreatmentReviewPost(
            @RequestParam(name = "page", defaultValue = "0")
            @PositiveOrZero(message = "페이지 수는 0 이상인 정수만 가능합니다.") final int page,
            @PathVariable("hospitalId") Long hospitalId,
            @PathVariable("doctorId") Long doctorId
    ) {
        List<GetAllDoctorTreatmentReviewPostResponse> responses = doctorTreatmentReviewService.getAllDoctorTreatmentReviewPosts(
                hospitalId,
                doctorId);
        return ResponseEntity.ok(doctorTreatmentReviewService.getAllDoctorTreatmentReviewPosts(hospitalId, doctorId));
    }

    @GetMapping("/total-reviews/doctors")
    public ResponseEntity<GetDoctorTreatmentReviewPostTotalResponse> getDoctorTreatmentReviewPostsTotal(
            @RequestParam(required = false) String keyword
    ) {
        GetDoctorTreatmentReviewPostTotalResponse response = integrationSearchService.getDoctorTreatmentReviewPostsTotal(keyword);

        return ResponseEntity.ok(response);
    }
}
