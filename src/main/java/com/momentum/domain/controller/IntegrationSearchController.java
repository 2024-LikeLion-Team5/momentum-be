//package com.momentum.domain.controller;
//
//import com.momentum.application.IntegrationSearchService;
//import com.momentum.domain.dto.response.GetDoctorReviewIntegrationSearchResponse;
//import com.momentum.domain.dto.response.GetDoctorTreatmentReviewPostTotalResponse;
//import com.momentum.domain.dto.response.GetHospitalIntegrationSearchResponse;
//import com.momentum.domain.dto.response.GetHospitalReviewPostTotalResponse;
//import jakarta.validation.constraints.PositiveOrZero;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class IntegrationSearchController {
//
//    private final IntegrationSearchService integrationSearchService;
//
//    @GetMapping("/doctor-reviews/integration")
//    public ResponseEntity<GetDoctorTreatmentReviewPostTotalResponse> getDoctorTreatmentReviewPostsTotal(
//            @RequestParam(required = false) String keyword
//    ) {
//        GetDoctorTreatmentReviewPostTotalResponse response = integrationSearchService.getDoctorTreatmentReviewPostsTotal(keyword);
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping("/doctor-reviews")
//    public ResponseEntity<List<GetDoctorReviewIntegrationSearchResponse>> getDoctorReviewPosts(
//            @RequestParam(required = false) String keyword,
//            @RequestParam(name = "page", defaultValue = "0")
//            @PositiveOrZero(message = "페이지 수는 0 이상인 정수만 가능합니다.") int page
//    ) {
//        List<GetDoctorReviewIntegrationSearchResponse> responses =
//                integrationSearchService.getDoctorReviewPosts(keyword, page);
//        return ResponseEntity.ok(responses);
//    }
//
//    @GetMapping("/hospitals/integration")
//    public ResponseEntity<GetHospitalReviewPostTotalResponse> getHospitalReviewPostTotal(
//            @RequestParam(required = false) String keyword) {
//        GetHospitalReviewPostTotalResponse response = integrationSearchService.getHospitalReviewPostTotal(keyword);
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping("/hospitals")
//    public ResponseEntity<List<GetHospitalIntegrationSearchResponse>> getHospitalPosts(
//            @RequestParam(required = false) String keyword,
//            @RequestParam(name = "page", defaultValue = "0")
//            @PositiveOrZero(message = "페이지 수는 0이상인 정수만 가능합니다.") int page
//    ) {
//        List<GetHospitalIntegrationSearchResponse> responses =
//                integrationSearchService.getHospitalPosts(keyword, page);
//        return ResponseEntity.ok(responses);
//    }
//}
