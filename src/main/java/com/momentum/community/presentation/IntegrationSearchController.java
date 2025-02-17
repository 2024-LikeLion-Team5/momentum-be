package com.momentum.community.presentation;

import com.momentum.community.application.IntegrationSearchService;
import com.momentum.community.dto.response.GetCommunityIntegrationSearchResponse;
import com.momentum.community.dto.response.GetCommunityPostTotalResponse;
import com.momentum.review.dto.response.GetAllHospitalInfoTotalResponse;
import com.momentum.review.dto.response.GetDoctorTreatmentReviewPostTotalResponse;
import com.momentum.review.dto.response.IntegrationDoctorReviewPostSearchDto;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IntegrationSearchController {

    private final IntegrationSearchService integrationSearchService;

    @GetMapping("/communities/integration")
    public ResponseEntity<GetCommunityPostTotalResponse> getCommunityPostsTotal(
            @RequestParam(required = false) String keyword) {
        GetCommunityPostTotalResponse response = integrationSearchService.getCommunityPostsTotal(keyword);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/communities")
    public ResponseEntity<List<GetCommunityIntegrationSearchResponse>> getCommunityPosts(
            @RequestParam(required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "0")
            @PositiveOrZero(message = "페이지 수는 0이상인 정수만 가능합니다.") int page
    ) {
        List<GetCommunityIntegrationSearchResponse> responses =
                integrationSearchService.getCommunityPosts(keyword, page);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/doctor-reviews/integration")
    public ResponseEntity<GetDoctorTreatmentReviewPostTotalResponse> getDoctorTreatmentReviewPostsTotal(
            @RequestParam(required = false) String keyword
    ) {
        GetDoctorTreatmentReviewPostTotalResponse response =
                integrationSearchService.getDoctorTreatmentReviewPostsTotal(keyword);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/doctor-reviews")
    public ResponseEntity<List<IntegrationDoctorReviewPostSearchDto>> getDoctorReviewPosts(
            @RequestParam(required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "0")
            @PositiveOrZero(message = "페이지 수는 0 이상인 정수만 가능합니다.") int page
    ) {
        List<IntegrationDoctorReviewPostSearchDto> responses =
                integrationSearchService.getDoctorReviewPostAll(keyword, page);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/hospitals/integration")
    public ResponseEntity<GetAllHospitalInfoTotalResponse> getHospitalReviewPostTotal(
            @RequestParam(required = false) String keyword
    ) {
        GetAllHospitalInfoTotalResponse response = integrationSearchService.getHospitalReviewPostTotal(keyword);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hospitals")
    public ResponseEntity<GetAllHospitalInfoTotalResponse> getHospitalPosts(
            @RequestParam(required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "0")
            @PositiveOrZero(message = "페이지 수는 0이상인 정수만 가능합니다.") int page
    ) {
        GetAllHospitalInfoTotalResponse response = integrationSearchService.getHospitalInfos(keyword, page);
        return ResponseEntity.ok(response);
    }
}
