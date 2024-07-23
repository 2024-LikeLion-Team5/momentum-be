package com.momentum.dto.request.comment;

public record CreateCommentRequest(
        String communityType,
        String content
) {
}
