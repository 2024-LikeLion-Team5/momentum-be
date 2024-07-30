package com.momentum.community.dto.request;

public record CreateDailyPostRequest(
        String title,
        String content
) {
}
