package com.momentum.dto.response.comment;

import com.momentum.domain.Comment;
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

    public record CommentResponse(
            long commentId,
            String comment
    ) {
    }
}
