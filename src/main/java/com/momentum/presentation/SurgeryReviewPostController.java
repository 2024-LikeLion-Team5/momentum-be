package com.momentum.presentation;

import com.momentum.application.SurgeryReviewPostService;
import com.momentum.dto.request.CreateSurgeryReviewPostRequest;
import com.momentum.dto.response.GetAllSurgeryReviewPostResponse;
import com.momentum.dto.response.GetSurgeryReviewPostResponse;
import jakarta.validation.constraints.PositiveOrZero;
import java.net.URI;
import java.util.List;
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
public class SurgeryReviewPostController {

    private final SurgeryReviewPostService surgeryReviewPostService;

    @PostMapping("/communities/surgery-reviews")
    public ResponseEntity<Void> createSurgeryReviewPost(@RequestBody CreateSurgeryReviewPostRequest request) {
        Long postId = surgeryReviewPostService.createSurgeryReviewPost(request);
        return ResponseEntity.created(URI.create("/posts/" + postId)).build();
    }

    @GetMapping("/communities/surgery-reviews/{postId}")
    public ResponseEntity<GetSurgeryReviewPostResponse> getSurgeryReviewPost(@PathVariable Long postId) {
        GetSurgeryReviewPostResponse response = surgeryReviewPostService.getSurgeryReviewPost(postId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/communities/surgery-reviews")
    public ResponseEntity<List<GetAllSurgeryReviewPostResponse>> getAllSurgeryReviewPosts(
            @RequestParam(name = "disease") String disease,
            @RequestParam(name = "page", defaultValue = "0")
            @PositiveOrZero(message = "페이지 수는 0이상인 정수만 가능합니다.") final int page
    ) {
        List<GetAllSurgeryReviewPostResponse> responses =
                surgeryReviewPostService.getAllSurgeryReviewPosts(disease, page);
        return ResponseEntity.ok(responses);
    }
}
