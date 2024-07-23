package com.momentum.presentation;

import com.momentum.application.DailyPostService;
import com.momentum.dto.request.community.CreateDailyPostRequest;
import com.momentum.dto.response.community.GetAllDailyPostResponse;
import com.momentum.dto.response.community.GetDailyPostResponse;
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


    @GetMapping("/communities/dailies")
    public ResponseEntity<List<GetAllDailyPostResponse>> getAllDailyPosts(
            @RequestParam(name = "page", defaultValue = "0")
            @PositiveOrZero(message = "페이지 수는 0이상인 정수만 가능합니다.") final int page
    ) {
        List<GetAllDailyPostResponse> responses = dailyPostService.getAllDailyPosts(page);
        return ResponseEntity.ok(responses);
    }
}
