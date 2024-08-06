package com.momentum.review.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record GetHospitalIntegrationSearchResponse(
        long postId,
        String title,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.")
        LocalDateTime createdAt,

        long likeCount,
        long hits
) {

    public static GetHospitalIntegrationSearchResponse from(
            IntegrationDoctorReviewPostSearchDto integrationDoctorReviewPostSearchDto
    ) {
        return new GetHospitalIntegrationSearchResponse(
                integrationDoctorReviewPostSearchDto.postId(),
                integrationDoctorReviewPostSearchDto.title(),
                integrationDoctorReviewPostSearchDto.createdAt(),
                integrationDoctorReviewPostSearchDto.likeCount(),
                integrationDoctorReviewPostSearchDto.hits()
        );
    }
}
