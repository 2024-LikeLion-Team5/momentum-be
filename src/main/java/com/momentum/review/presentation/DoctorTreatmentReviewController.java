package com.momentum.review.presentation;

import com.momentum.community.application.IntegrationSearchService;
import com.momentum.review.application.DoctorTreatmentReviewService;
import com.momentum.review.dto.request.CreateDoctorTreatmentReviewPostRequest;
import com.momentum.review.dto.response.GetAllDoctorTreatmentReviewPostResponse;
import com.momentum.review.dto.response.GetDoctorTreatmentReviewPostResponse;
import com.momentum.review.dto.response.GetDoctorTreatmentReviewPostTotalResponse;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DoctorTreatmentReviewController {

    private final IntegrationSearchService integrationSearchService;
    private final DoctorTreatmentReviewService doctorTreatmentReviewService;

    @PostMapping("/hospital-reviews/doctor-reviews")
    public ResponseEntity<Void> createDoctorTreatmentReviewPost(
            @Valid @RequestBody CreateDoctorTreatmentReviewPostRequest request
    ) {
        Long postId = doctorTreatmentReviewService.createDoctorTreatmentReviewPost(request);
        return ResponseEntity.created(URI.create("/posts/" + postId)).build();
    }

    @GetMapping("/hospital-reviews/doctor-reviews/{postId}")
    public ResponseEntity<GetDoctorTreatmentReviewPostResponse> getDoctorTreatmentReviewPost(
            @PathVariable Long postId
    ) {
        GetDoctorTreatmentReviewPostResponse response =
                doctorTreatmentReviewService.getDoctorTreatmentReviewPost(postId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hospital-reviews/{hospitalId}/doctor-reviews/{doctorId}")
    public ResponseEntity<List<GetAllDoctorTreatmentReviewPostResponse>> getAllDoctorTreatmentReviewPost(
            @PathVariable("hospitalId") Long hospitalId,
            @PathVariable("doctorId") Long doctorId
    ) {
        List<GetAllDoctorTreatmentReviewPostResponse> responses =
                doctorTreatmentReviewService.getAllDoctorTreatmentReviewPosts(hospitalId, doctorId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/total-reviews/doctors")
    public ResponseEntity<GetDoctorTreatmentReviewPostTotalResponse> getDoctorTreatmentReviewPostsTotal(
            @RequestParam(required = false) String keyword
    ) {
        GetDoctorTreatmentReviewPostTotalResponse response =
                integrationSearchService.getDoctorTreatmentReviewPostsTotal(keyword);
        return ResponseEntity.ok(response);
    }
}
