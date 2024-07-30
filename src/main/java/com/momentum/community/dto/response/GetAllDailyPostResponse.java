package com.momentum.community.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.momentum.community.domain.DailyPost;
import java.time.LocalDateTime;

public record GetAllDailyPostResponse(
        long postId,
        String title,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.")
        LocalDateTime createdAt,

        long likeCount,
        long hits,
        boolean isNotice
) {

    public static GetAllDailyPostResponse from(DailyPost dailyPost) {
        return new GetAllDailyPostResponse(
                dailyPost.getId(),
                dailyPost.getTitle(),
                dailyPost.getCreatedAt(),
                dailyPost.getLikes(),
                dailyPost.getHits(),
                dailyPost.isNotice()
        );
    }
}
