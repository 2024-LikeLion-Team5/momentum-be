package com.momentum.domain.dto.response;

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
            IntegrationDoctorReviewSearchDto integrationDoctorReviewSearchDto
    ) {
        return new GetHospitalIntegrationSearchResponse(
                integrationDoctorReviewSearchDto.postId(),
                integrationDoctorReviewSearchDto.title(),
                integrationDoctorReviewSearchDto.createdAt(),
                integrationDoctorReviewSearchDto.likeCount(),
                integrationDoctorReviewSearchDto.hits()
        );
    }
}
