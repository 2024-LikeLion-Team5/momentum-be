package com.momentum.dto.response.community;

import com.momentum.domain.DailyPost;

public record GetDailyPostTotalResponse(
        long postId,
        String title
) {

    public static GetDailyPostTotalResponse from(DailyPost dailyPost) {
        return new GetDailyPostTotalResponse(
                dailyPost.getId(),
                dailyPost.getTitle()
        );
    }
}
