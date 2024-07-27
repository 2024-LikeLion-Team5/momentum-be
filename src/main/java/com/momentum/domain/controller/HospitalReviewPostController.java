package com.momentum.domain.controller;

import com.momentum.domain.dto.request.CreateHospitalReviewPostRequest;
import com.momentum.domain.dto.response.GetAllHospitalReviewPostResponse;
import com.momentum.domain.dto.response.GetDoctorTreatmentReviewPostTotalResponse;
import com.momentum.domain.dto.response.GetHospitalReviewPostResponse;
import com.momentum.domain.dto.response.GetHospitalReviewPostTotalResponse;
import com.momentum.domain.service.HospitalReviewPostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class HospitalReviewPostController {

    private final HospitalInfoController hospitalInfoController;

    private final HospitalReviewPostService hospitalReviewPostService;

    @PostMapping("/hospital-reviews/by-hospital-reviews")
    public ResponseEntity<Void> createHospitalReviewPost(@Valid @RequestBody CreateHospitalReviewPostRequest request) {
        Long postId = hospitalReviewPostService.createHospitalReviewPost(request);
        return ResponseEntity.created(URI.create("/posts/" + postId)).build();
    }

    @GetMapping("/hospital-reviews/by-hospital-reviews/{postId}")
    public ResponseEntity<GetHospitalReviewPostResponse> getHospitalReviewPost(@PathVariable Long postId) {
        GetHospitalReviewPostResponse responses = hospitalReviewPostService.getHospitalReviewPost(postId);

        return ResponseEntity.ok(responses);
    }

    // 이곳도 약간 문제입니다 ..
    @GetMapping("/hospital-reviews/{hospitalId}/by-hospital-reviews")
    public ResponseEntity<List<GetAllHospitalReviewPostResponse>> getAllHospitalReviewPost(
            @RequestParam(name = "page", defaultValue = "0")
            @PositiveOrZero(message = "페이지 수는 0 이상인 정수만 가능합니다.") final int page,
            @PathVariable("hospitalId") Long hospitalId
    ) {
        List<GetAllHospitalReviewPostResponse> responses = hospitalReviewPostService.getAllHospitalReviewPosts(page, hospitalId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/total-reviews/hospitals")
    public ResponseEntity<List<GetHospitalReviewPostTotalResponse>> getHospitalReviewPostsTotal(
            @RequestParam(required = false) String keyword
    ) {
        List<GetHospitalReviewPostTotalResponse> responses;

        if (keyword != null && !keyword.trim().isEmpty()) {
            // 검색어가 제공된 경우
            responses = hospitalReviewPostService.getHospitalReviewPosts(keyword);
        } else {
            responses = hospitalReviewPostService.getHospitalReviewPostsTotal();
        }
        return ResponseEntity.ok(responses);
    }
}
