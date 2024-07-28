package com.momentum.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.momentum.domain.entity.DoctorTreatmentReviewPost;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetDoctorTreatmentReviewPostTotalResponse {

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

    public static GetDoctorTreatmentReviewPostTotalResponse of(DoctorTreatmentReviewPost doctorTreatmentReviewPost) {
        return GetDoctorTreatmentReviewPostTotalResponse.builder()
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

    public static GetDoctorTreatmentReviewPostTotalResponse of(long totalSearchedCount, DoctorTreatmentReviewPost doctorTreatmentReviewPost) {
        return new GetDoctorTreatmentReviewPostTotalResponse(
                doctorTreatmentReviewPost.getId(),
                doctorTreatmentReviewPost.getTitle(),
                doctorTreatmentReviewPost.getCreatedAt(),
                doctorTreatmentReviewPost.getDisease(),
                doctorTreatmentReviewPost.getTreatment(),
                doctorTreatmentReviewPost.getDoctor(),
                doctorTreatmentReviewPost.getAgeGroup(),
                doctorTreatmentReviewPost.getRating(),
                doctorTreatmentReviewPost.getContent()
                );
    }
}
