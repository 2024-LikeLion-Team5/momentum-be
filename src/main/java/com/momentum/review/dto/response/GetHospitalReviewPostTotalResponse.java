package com.momentum.review.dto.response;

import com.momentum.review.domain.HospitalReviewPost;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetHospitalReviewPostTotalResponse {

    private Long postId;
    private String title;
    private String hospital;
    private String treatment;
    private double facilityRating;
    private double atmosphereRating;
    private double employeeRating;
    private String content;

    public static GetHospitalReviewPostTotalResponse from(HospitalReviewPost hospitalReviewPost) {
        return GetHospitalReviewPostTotalResponse.builder()
                .postId(hospitalReviewPost.getId())
                .title(hospitalReviewPost.getTitle())
                .hospital(hospitalReviewPost.getHospital())
                .treatment(hospitalReviewPost.getTreatment())
                .facilityRating(hospitalReviewPost.getFacilityRating())
                .atmosphereRating(hospitalReviewPost.getAtmosphereRating())
                .employeeRating(hospitalReviewPost.getEmployeeRating())
                .content(hospitalReviewPost.getContent())
                .build();
    }
}
