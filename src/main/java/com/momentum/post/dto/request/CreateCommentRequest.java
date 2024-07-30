package com.momentum.post.dto.request;

public record CreateCommentRequest(
        String communityType,
        String content
) {
}
