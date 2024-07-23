package com.momentum.presentation;

import com.momentum.application.DailyPostService;
import com.momentum.dto.request.community.CreateDailyPostRequest;
import com.momentum.dto.response.community.GetDailyPostResponse;
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
public class DailyPostController {

    private final DailyPostService dailyPostService;

    @PostMapping("/communities/dailies")
    public ResponseEntity<Void> createDailyPost(@RequestBody CreateDailyPostRequest request) {
        Long postId = dailyPostService.createDailyPost(request);
        return ResponseEntity.created(URI.create("/posts/" + postId)).build();
    }

    @GetMapping("/communities/dailies/{postId}")
    public ResponseEntity<GetDailyPostResponse> getDailyPost(@PathVariable Long postId) {
        GetDailyPostResponse response = dailyPostService.getDailyPost(postId);
        return ResponseEntity.ok(response);
    }
}
