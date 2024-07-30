package com.momentum.review.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.momentum.review.domain.DoctorTreatmentReviewPost;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetAllDoctorTreatmentReviewPostResponse {

    private Long postId;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.")
    private LocalDateTime createdAt;
    private String disease;
    private String treatment;
    private String doctor;
    private int ageGroup;
    private double rating;
    private String content;

    public static GetAllDoctorTreatmentReviewPostResponse of(DoctorTreatmentReviewPost doctorTreatmentReviewPost) {
        return GetAllDoctorTreatmentReviewPostResponse.builder()
                .postId(doctorTreatmentReviewPost.getId())
                .title(doctorTreatmentReviewPost.getTitle())
                .createdAt(doctorTreatmentReviewPost.getCreatedAt())
                .disease(doctorTreatmentReviewPost.getDisease())
                .treatment(doctorTreatmentReviewPost.getTreatment())
                .doctor(doctorTreatmentReviewPost.getDoctor())
                .ageGroup(doctorTreatmentReviewPost.getAgeGroup())
                .rating(doctorTreatmentReviewPost.getRating())
                .content(doctorTreatmentReviewPost.getContent())
                .build();
    }
}
