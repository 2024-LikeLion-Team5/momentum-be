package com.momentum.community.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.momentum.community.domain.SurgeryReviewPost;
import java.time.LocalDateTime;

public record GetAllSurgeryReviewPostResponse(
        long postId,
        String surgery,
        String title,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.")
        LocalDateTime createdAt,

        long likeCount,
        long hits,
        boolean isNotice
) {

    public static GetAllSurgeryReviewPostResponse from(SurgeryReviewPost surgeryReviewPost) {
        return new GetAllSurgeryReviewPostResponse(
                surgeryReviewPost.getId(),
                surgeryReviewPost.getSurgery(),
                surgeryReviewPost.getTitle(),
                surgeryReviewPost.getCreatedAt(),
                surgeryReviewPost.getLikes(),
                surgeryReviewPost.getHits(),
                surgeryReviewPost.isNotice()
        );
    }
}
