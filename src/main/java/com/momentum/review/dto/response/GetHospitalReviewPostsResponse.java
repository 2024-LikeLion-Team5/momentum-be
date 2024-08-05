package com.momentum.review.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.momentum.review.domain.HospitalReviewPost;
import java.time.LocalDateTime;

public record GetHospitalReviewPostsResponse(
        Long postId,
        String title,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.")
        LocalDateTime createdAt,
        String treatment,
        double facilityRating,
        double atmosphereRating,
        double employeeRating,
        String content
) {

    public static GetHospitalReviewPostsResponse from(HospitalReviewPost hospitalReviewPost) {
        return new GetHospitalReviewPostsResponse(
                hospitalReviewPost.getId(),
                hospitalReviewPost.getTitle(),
                hospitalReviewPost.getCreatedAt(),
                hospitalReviewPost.getTreatment(),
                hospitalReviewPost.getFacilityRating(),
                hospitalReviewPost.getAtmosphereRating(),
                hospitalReviewPost.getEmployeeRating(),
                hospitalReviewPost.getContent()
        );
    }
}
