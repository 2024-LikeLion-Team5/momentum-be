package com.momentum.review.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.momentum.review.domain.HospitalReviewPost;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetHospitalReviewPostResponse {

    private String communityType;
    private String title;
    private long hits;
    private long likeCount;
    private long dislikeCount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.")
    LocalDateTime createdAt;
    private String treatment;
    private String hospital;
    private double facilityRating;
    private double atmosphereRating;
    private double employeeRating;
    private String content;

    public static GetHospitalReviewPostResponse of(HospitalReviewPost hospitalReviewPost) {
        return GetHospitalReviewPostResponse.builder()
                .communityType("HOSPITAL_REVIEW")
                .title(hospitalReviewPost.getTitle())
                .hits(hospitalReviewPost.getHits())
                .likeCount(hospitalReviewPost.getLikes())
                .dislikeCount(hospitalReviewPost.getDislikes())
                .createdAt(hospitalReviewPost.getCreatedAt())
                .treatment(hospitalReviewPost.getTreatment())
                .hospital(hospitalReviewPost.getHospital())
                .facilityRating(hospitalReviewPost.getFacilityRating())
                .atmosphereRating(hospitalReviewPost.getAtmosphereRating())
                .employeeRating(hospitalReviewPost.getEmployeeRating())
                .content(hospitalReviewPost.getContent())
                .build();
    }
}
