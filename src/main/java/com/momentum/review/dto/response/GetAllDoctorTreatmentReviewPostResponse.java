package com.momentum.review.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.momentum.community.domain.vo.Disease;
import com.momentum.review.domain.DoctorTreatmentReviewPost;
import com.momentum.review.domain.vo.AgeGroup;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAllDoctorTreatmentReviewPostResponse {

    private Long postId;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.")
    private LocalDateTime createdAt;
    private Disease disease;
    private String treatment;
    private String doctor;
    private AgeGroup ageGroup;
    private double rating;
    private String content;

    public static GetAllDoctorTreatmentReviewPostResponse from(DoctorTreatmentReviewPost doctorTreatmentReviewPost) {
        return GetAllDoctorTreatmentReviewPostResponse.builder()
                .postId(doctorTreatmentReviewPost.getId())
                .title(doctorTreatmentReviewPost.getTitle())
                .createdAt(doctorTreatmentReviewPost.getCreatedAt())
                .disease(doctorTreatmentReviewPost.getDisease())
                .treatment(doctorTreatmentReviewPost.getTreatment())
                .doctor(doctorTreatmentReviewPost.getDoctorName())
                .ageGroup(doctorTreatmentReviewPost.getAgeGroup())
                .rating(doctorTreatmentReviewPost.getRating())
                .content(doctorTreatmentReviewPost.getContent())
                .build();
    }
}
