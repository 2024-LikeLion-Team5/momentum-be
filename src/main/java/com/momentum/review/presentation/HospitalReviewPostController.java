package com.momentum.review.presentation;

import com.momentum.review.application.HospitalReviewPostService;
import com.momentum.review.dto.request.CreateHospitalReviewPostRequest;
import com.momentum.review.dto.response.GetDoctorResponse;
import com.momentum.review.dto.response.GetHospitalReviewPostResponse;
import com.momentum.review.dto.response.GetHospitalReviewPostTotalResponse;
import com.momentum.review.dto.response.GetHospitalReviewPostsResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
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
public class HospitalReviewPostController {

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

    @GetMapping("/hospital-reviews/{hospitalId}/by-hospital-reviews")
    public ResponseEntity<List<GetHospitalReviewPostsResponse>> getAllHospitalReviewPost(
            @RequestParam(name = "page", defaultValue = "0")
            @PositiveOrZero(message = "페이지 수는 0 이상인 정수만 가능합니다.") final int page,
            @PathVariable("hospitalId") Long hospitalId
    ) {
        List<GetHospitalReviewPostsResponse> responses =
                hospitalReviewPostService.getAllHospitalReviewPosts(page, hospitalId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/hospitals/{hospitalId}/doctors")
    public ResponseEntity<List<GetDoctorResponse>> getDoctors(@PathVariable Long hospitalId) {
        List<GetDoctorResponse> responses = hospitalReviewPostService.getDoctors(hospitalId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/total-reviews/hospitals")
    public ResponseEntity<List<GetHospitalReviewPostTotalResponse>> getHospitalReviewPostsTotal() {
        List<GetHospitalReviewPostTotalResponse> responses = hospitalReviewPostService.getHospitalReviewPostsTotal();
        return ResponseEntity.ok(responses);
    }
}
