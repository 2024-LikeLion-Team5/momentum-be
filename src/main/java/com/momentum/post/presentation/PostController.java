package com.momentum.post.presentation;

import com.momentum.post.application.PostService;
import com.momentum.post.dto.request.UpdateDislikeRequest;
import com.momentum.post.dto.request.UpdateLikesRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PatchMapping("/posts/{postId}/like")
    public ResponseEntity<Void> like(@PathVariable Long postId, @RequestBody UpdateLikesRequest request) {
        postService.like(postId, request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/posts/{postId}/dislike")
    public ResponseEntity<Void> dislike(@PathVariable Long postId, @RequestBody UpdateDislikeRequest request) {
        postService.dislike(postId, request);
        return ResponseEntity.ok().build();
    }
}
