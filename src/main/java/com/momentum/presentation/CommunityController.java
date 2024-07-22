package com.momentum.presentation;

import com.momentum.application.CommunityService;
import com.momentum.dto.request.CreateConcernPostRequest;
import com.momentum.dto.response.GetConcernPostResponse;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    @PostMapping("/communities/concerns")
    public ResponseEntity<Void> createConcernPost(@Valid @RequestBody CreateConcernPostRequest request) {
        Long postId = communityService.createConcernPost(request);
        return ResponseEntity.created(URI.create("/posts/" + postId)).build();
    }

    @GetMapping("/communities/concerns/{postId}")
    public ResponseEntity<GetConcernPostResponse> getConcernPost(@PathVariable Long postId) {
        GetConcernPostResponse response = communityService.getConcernPost(postId);
        return ResponseEntity.ok(response);
    }
}
