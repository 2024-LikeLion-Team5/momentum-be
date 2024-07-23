package com.momentum.presentation;

import com.momentum.application.IntegrationSearchService;
import com.momentum.dto.response.community.GetCommunityIntegrationSearchResponse;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IntegrationSearchController {

    private final IntegrationSearchService integrationSearchService;

    @GetMapping("/communities")
    public ResponseEntity<List<GetCommunityIntegrationSearchResponse>> getCommunityPosts(
            @RequestParam(required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "0")
            @PositiveOrZero(message = "페이지 수는 0이상인 정수만 가능합니다.") int page
    ) {
        List<GetCommunityIntegrationSearchResponse> responses =
                integrationSearchService.getCommunityPosts(keyword, page);
        return ResponseEntity.ok(responses);
    }
}
