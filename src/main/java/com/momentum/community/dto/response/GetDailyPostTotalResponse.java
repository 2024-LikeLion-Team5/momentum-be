package com.momentum.community.dto.response;

import com.momentum.community.domain.DailyPost;

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
