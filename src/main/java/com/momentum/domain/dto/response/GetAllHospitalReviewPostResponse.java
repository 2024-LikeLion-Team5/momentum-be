package com.momentum.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.momentum.domain.entity.HospitalReviewPost;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetAllHospitalReviewPostResponse {

    private Long postId;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.")
    LocalDateTime createdAt;
    private String treatment;
    private double facilityRating;
    private double atmosphereRating;
    private double employeeRating;
    private String content;

    public static GetAllHospitalReviewPostResponse of(HospitalReviewPost hospitalReviewPost) {
        return GetAllHospitalReviewPostResponse.builder()
                .postId(hospitalReviewPost.getId())
                .title(hospitalReviewPost.getTitle())
                .createdAt(hospitalReviewPost.getCreatedAt())
                .treatment(hospitalReviewPost.getTreatment())
                .facilityRating(hospitalReviewPost.getFacilityRating())
                .atmosphereRating(hospitalReviewPost.getAtmosphereRating())
                .employeeRating(hospitalReviewPost.getEmployeeRating())
                .content(hospitalReviewPost.getContent())
                .build();
    }
}
