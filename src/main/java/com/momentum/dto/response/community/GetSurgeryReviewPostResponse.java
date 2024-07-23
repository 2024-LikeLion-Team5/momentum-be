package com.momentum.dto.response.community;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.momentum.domain.SurgeryReviewPost;
import com.momentum.domain.vo.Disease;
import java.time.LocalDateTime;

public record GetSurgeryReviewPostResponse(
        Disease disease,
        String surgery,
        String title,
        String content,
        long likeCount,
        long dislikeCount,
        long hits,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.")
        LocalDateTime createdAt
) {

    public static GetSurgeryReviewPostResponse from(SurgeryReviewPost surgeryReviewPost) {
        return new GetSurgeryReviewPostResponse(
                surgeryReviewPost.getDisease(),
                surgeryReviewPost.getSurgery(),
                surgeryReviewPost.getTitle(),
                surgeryReviewPost.getContent(),
                surgeryReviewPost.getLikes(),
                surgeryReviewPost.getDislikes(),
                surgeryReviewPost.getHits(),
                surgeryReviewPost.getCreatedAt()
        );
    }
}
