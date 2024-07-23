package com.momentum.dto.response.community;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.momentum.domain.DailyPost;
import java.time.LocalDateTime;

public record GetDailyPostResponse(
        String title,
        String content,
        long likeCount,
        long dislikeCount,
        long hits,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.")
        LocalDateTime createdAt
) {

    public static GetDailyPostResponse from(DailyPost dailyPost) {
        return new GetDailyPostResponse(
                dailyPost.getTitle(),
                dailyPost.getContent(),
                dailyPost.getLikes(),
                dailyPost.getDislikes(),
                dailyPost.getHits(),
                dailyPost.getCreatedAt()
        );
    }
}
