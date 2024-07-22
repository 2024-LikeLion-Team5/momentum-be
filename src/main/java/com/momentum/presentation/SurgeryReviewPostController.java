package com.momentum.presentation;

import com.momentum.application.SurgeryReviewPostService;
import com.momentum.dto.request.CreateSurgeryReviewPostRequest;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SurgeryReviewPostController {

    private final SurgeryReviewPostService surgeryReviewPostService;

    @PostMapping("/communities/surgery-reviews")
    public ResponseEntity<Void> createSurgeryReviewPost(@RequestBody CreateSurgeryReviewPostRequest request) {
        Long postId = surgeryReviewPostService.createSurgeryReviewPost(request);
        return ResponseEntity.created(URI.create("/posts/" + postId)).build();
    }
}
