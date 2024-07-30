package com.momentum.review.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record GetDoctorReviewIntegrationSearchResponse(
        long postId,
        String title,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.")
        LocalDateTime createdAt,

        long likeCount,
        long hits
) {

    public static GetDoctorReviewIntegrationSearchResponse from(
            IntegrationDoctorReviewSearchDto integrationDoctorReviewSearchDto
    ) {
        return new GetDoctorReviewIntegrationSearchResponse(
                integrationDoctorReviewSearchDto.postId(),
                integrationDoctorReviewSearchDto.title(),
                integrationDoctorReviewSearchDto.createdAt(),
                integrationDoctorReviewSearchDto.likeCount(),
                integrationDoctorReviewSearchDto.hits()
        );
    }
}
