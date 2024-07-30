package com.momentum.post.dto.response;

import com.momentum.post.domain.Comment;
import java.util.List;

public record GetAllCommentResponses(
        long totalCommentCount,
        List<CommentResponse> comments
) {

    public static GetAllCommentResponses of(long totalCommentCount, List<Comment> comments) {
        List<CommentResponse> commentResponses = comments.stream()
                .map(comment -> new CommentResponse(comment.getId(), comment.getContent()))
                .toList();
        return new GetAllCommentResponses(totalCommentCount, commentResponses);
    }

    private record CommentResponse(
            long commentId,
            String comment
    ) {
    }
}