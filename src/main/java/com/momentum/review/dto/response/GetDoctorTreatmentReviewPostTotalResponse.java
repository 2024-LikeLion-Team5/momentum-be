package com.momentum.review.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record GetDoctorTreatmentReviewPostTotalResponse(
        long totalSearchedCount,
        List<DoctorTreatmentReviewPostsResponse> doctorTreatmentReviewPostsResponses
) {

    public static GetDoctorTreatmentReviewPostTotalResponse of(
            long totalSearchedCount,
            List<IntegrationDoctorReviewSearchDto> integrationDoctorReviewSearchDtos
    ) {
        List<DoctorTreatmentReviewPostsResponse> doctorTreatmentReviewPostsResponses = integrationDoctorReviewSearchDtos.stream()
                .map(it -> new DoctorTreatmentReviewPostsResponse(
                        it.postId(),
                        it.title(),
                        it.createdAt(),
                        it.disease(),
                        it.treatment(),
                        it.doctor(),
                        it.ageGroup(),
                        it.rating(),
                        it.content()
                )).toList();

        return new GetDoctorTreatmentReviewPostTotalResponse(totalSearchedCount, doctorTreatmentReviewPostsResponses);
    }

    private record DoctorTreatmentReviewPostsResponse(
            Long id,
            String title,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.")
            LocalDateTime createdAt,
            String disease,
            String treatment,
            String doctor,
            int ageGroup,
            double rating,
            String content
    ) {
    }
}

//    public static GetDoctorTreatmentReviewPostTotalResponse of(DoctorTreatmentReviewPost doctorTreatmentReviewPost) {
//        return GetDoctorTreatmentReviewPostTotalResponse.builder()
//                .postId(doctorTreatmentReviewPost.getId())
//                .title(doctorTreatmentReviewPost.getTitle())
//                .createdAt(doctorTreatmentReviewPost.getCreatedAt())
//                .disease(doctorTreatmentReviewPost.getDisease())
//                .treatment(doctorTreatmentReviewPost.getTreatment())
//                .doctor(doctorTreatmentReviewPost.getDoctor())
//                .ageGroup(doctorTreatmentReviewPost.getAgeGroup())
//                .rating(doctorTreatmentReviewPost.getRating())
//                .content(doctorTreatmentReviewPost.getContent())
//                .build();
//    }

//    @Data
//    @Builder
//    @NoArgsConstructor
//    @AllArgsConstructor
//    private static class DoctorTreatmentReviewPostResponse {
//
//        private Long id;
//        private String title;
//        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.")
//        private LocalDateTime createdAt;
//        private String disease;
//        private String treatment;
//        private String doctor;
//        private int ageGroup;
//        private double rating;
//        private String content;
//
//        public static DoctorTreatmentReviewPostResponse of(DoctorTreatmentReviewPost doctorTreatmentReviewPost) {
//            return DoctorTreatmentReviewPostResponse.builder()
//                    .id(doctorTreatmentReviewPost.getId())
//                    .title(doctorTreatmentReviewPost.getTitle())
//                    .createdAt(doctorTreatmentReviewPost.getCreatedAt())
//                    .disease(doctorTreatmentReviewPost.getDisease())
//                    .treatment(doctorTreatmentReviewPost.getTreatment())
//                    .doctor(doctorTreatmentReviewPost.getDoctor())
//                    .ageGroup(doctorTreatmentReviewPost.getAgeGroup())
//                    .rating(doctorTreatmentReviewPost.getRating())
//                    .content(doctorTreatmentReviewPost.getContent())
//                    .build();
//        }
//    }

//        return GetDoctorTreatmentReviewPostTotalResponse.builder()
//                .totalSearchedCount(totalSearchedCount)
//                .doctorTreatmentReviewPostsResponses(doctorTreatmentReviewPostsResponses)
//                .build();
