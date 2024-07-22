package com.momentum.dto.response.community;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.momentum.domain.ConcernPost;
import com.momentum.domain.vo.Disease;
import java.time.LocalDateTime;

public record GetConcernPostResponse(
        String communityType,
        Disease disease,
        String title,
        String content,
        long likeCount,
        long dislikeCount,
        long hits,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.")
        LocalDateTime createdAt
) {

    public static GetConcernPostResponse from(ConcernPost concernPost) {
        return new GetConcernPostResponse(
                "CONCERN",
                concernPost.getDisease(),
                concernPost.getTitle(),
                concernPost.getContent(),
                concernPost.getLikes(),
                concernPost.getDislikes(),
                concernPost.getHits(),
                concernPost.getCreatedAt()
        );
    }
}
