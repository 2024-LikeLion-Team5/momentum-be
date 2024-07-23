package com.momentum.dto.request.community;

public record CreateDailyPostRequest(
        String title,
        String content
) {
}
