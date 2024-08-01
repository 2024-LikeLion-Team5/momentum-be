package com.momentum.review.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.momentum.community.domain.vo.Disease;
import com.momentum.post.domain.vo.PostType;
import com.momentum.review.domain.DoctorTreatmentReviewPost;
import com.momentum.review.domain.vo.AgeGroup;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public record IntegrationDoctorReviewSearchDto(
        long postId,
        String title,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.")
        LocalDateTime createdAt,
        Disease disease,
        String treatment,
        String doctor,
        AgeGroup ageGroup,
        double rating,
        String content,
        long likeCount,
        long hits,
        PostType postType
) {

    public static IntegrationDoctorReviewSearchDto from(DoctorTreatmentReviewPost doctorTreatmentReviewPost) {
        return new IntegrationDoctorReviewSearchDto(
                doctorTreatmentReviewPost.getId(),
                doctorTreatmentReviewPost.getTitle(),
                doctorTreatmentReviewPost.getCreatedAt(),
                doctorTreatmentReviewPost.getDisease(),
                doctorTreatmentReviewPost.getTreatment(),
                doctorTreatmentReviewPost.getDoctor(),
                doctorTreatmentReviewPost.getAgeGroup(),
                doctorTreatmentReviewPost.getRating(),
                doctorTreatmentReviewPost.getContent(),
                doctorTreatmentReviewPost.getLikes(),
                doctorTreatmentReviewPost.getHits(),
                doctorTreatmentReviewPost.getPostType()
        );
    }

    public static IntegrationDoctorReviewSearchDto from(Object[] query) {
        return new IntegrationDoctorReviewSearchDto(
                ((Number) query[0]).longValue(),
                (String) query[1],
                ((Timestamp) query[2]).toLocalDateTime(),
                Disease.valueOf((String) query[3]),
                ((String) query[4]),
                ((String) query[5]),
                AgeGroup.valueOf((String) query[6]),
                ((Number) query[7]).doubleValue(),
                ((String) query[8]),
                ((Number) query[9]).longValue(),
                ((Number) query[10]).longValue(),
                PostType.valueOf((String) query[11])
        );
    }
}
