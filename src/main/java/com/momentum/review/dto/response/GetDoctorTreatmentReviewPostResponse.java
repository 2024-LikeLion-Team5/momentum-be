package com.momentum.review.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.momentum.review.domain.DoctorTreatmentReviewPost;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetDoctorTreatmentReviewPostResponse {

    private String title;
    private Long hits;
    private Long likeCount;
    private Long dislikeCount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.")
    private LocalDateTime createdAt;
    private String disease;
    private String treatment;
    private int ageGroup;
    private double rating;
    private String doctorName;
    private String content;

    public static GetDoctorTreatmentReviewPostResponse of(DoctorTreatmentReviewPost doctorTreatmentReviewPost) {
        return GetDoctorTreatmentReviewPostResponse.builder()
                .title(doctorTreatmentReviewPost.getTitle())
                .hits(doctorTreatmentReviewPost.getHits())
                .likeCount(doctorTreatmentReviewPost.getLikes())
                .dislikeCount(doctorTreatmentReviewPost.getDislikes())
                .createdAt(doctorTreatmentReviewPost.getCreatedAt())
                .disease(doctorTreatmentReviewPost.getDisease())
                .treatment(doctorTreatmentReviewPost.getTreatment())
                .ageGroup(doctorTreatmentReviewPost.getAgeGroup())
                .rating(doctorTreatmentReviewPost.getRating())
                .doctorName(doctorTreatmentReviewPost.getDoctor())
                .content(doctorTreatmentReviewPost.getContent())
                .build();
    }
}
