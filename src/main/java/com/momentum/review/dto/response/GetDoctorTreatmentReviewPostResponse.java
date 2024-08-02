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
public class GetDoctorTreatmentReviewPostResponse {

    private String title;
    private Long hits;
    private Long likeCount;
    private Long dislikeCount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.")
    private LocalDateTime createdAt;
    private Disease disease;
    private String treatment;
    private AgeGroup ageGroup;
    private double rating;
    private String doctorName;
    private String content;

    public static GetDoctorTreatmentReviewPostResponse from(DoctorTreatmentReviewPost doctorTreatmentReviewPost) {
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
                .doctorName(doctorTreatmentReviewPost.getDoctorName())
                .content(doctorTreatmentReviewPost.getContent())
                .build();
    }
}
