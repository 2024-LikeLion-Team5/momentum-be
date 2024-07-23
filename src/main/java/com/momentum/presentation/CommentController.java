package com.momentum.presentation;

import com.momentum.application.CommentService;
import com.momentum.dto.request.comment.CreateCommentRequest;
import com.momentum.dto.response.comment.GetAllCommentResponses;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Void> createComment(
            @PathVariable("postId") Long postId,
            @RequestBody CreateCommentRequest request
    ) {
        Long commentId = commentService.createComment(postId, request);
        return ResponseEntity.created(URI.create("/posts/" + postId + "/comments/" + commentId)).build();
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<GetAllCommentResponses> getAllComments(
            @PathVariable("postId") Long postId,
            @RequestParam(name = "communityType") String communityType
    ) {
        GetAllCommentResponses responses = commentService.getAllComments(postId, communityType);
        return ResponseEntity.ok(responses);
    }
}
