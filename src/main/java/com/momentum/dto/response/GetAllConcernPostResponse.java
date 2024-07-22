package com.momentum.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.momentum.domain.ConcernPost;
import java.time.LocalDateTime;

public record GetAllConcernPostResponse(
        long postId,
        String title,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.")
        LocalDateTime createdAt,

        long likeCount,
        long hits,
        boolean isNotice
) {

    public static GetAllConcernPostResponse from(ConcernPost concernPost) {
        return new GetAllConcernPostResponse(
                concernPost.getId(),
                concernPost.getTitle(),
                concernPost.getCreatedAt(),
                concernPost.getLikes(),
                concernPost.getHits(),
                concernPost.isNotice()
        );
    }
}
