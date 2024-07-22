package com.momentum.presentation;

import com.momentum.application.ConcernPostService;
import com.momentum.domain.vo.Disease;
import com.momentum.dto.request.CreateConcernPostRequest;
import com.momentum.dto.response.GetAllConcernPostResponse;
import com.momentum.dto.response.GetAllDiseaseResponse;
import com.momentum.dto.response.GetConcernPostResponse;
import com.momentum.dto.response.GetConcernPostTotalResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import java.net.URI;
import java.util.Arrays;
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
public class ConcernPostController {

    private final ConcernPostService concernPostService;

    @PostMapping("/communities/concerns")
    public ResponseEntity<Void> createConcernPost(@Valid @RequestBody CreateConcernPostRequest request) {
        Long postId = concernPostService.createConcernPost(request);
        return ResponseEntity.created(URI.create("/posts/" + postId)).build();
    }

    @GetMapping("/communities/concerns/{postId}")
    public ResponseEntity<GetConcernPostResponse> getConcernPost(@PathVariable Long postId) {
        GetConcernPostResponse response = concernPostService.getConcernPost(postId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/communities/concerns")
    public ResponseEntity<List<GetAllConcernPostResponse>> getAllConcernPosts(
            @RequestParam(name = "disease") String disease,
            @RequestParam(name = "page", defaultValue = "0")
            @PositiveOrZero(message = "페이지 수는 0이상인 정수만 가능합니다.") final int page
    ) {
        List<GetAllConcernPostResponse> responses = concernPostService.getAllConcernPosts(disease, page);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/total-communities/concerns")
    public ResponseEntity<List<GetConcernPostTotalResponse>> getTotalConcernPosts() {
        List<GetConcernPostTotalResponse> responses = concernPostService.getConcernPostsTotal();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/disease")
    public ResponseEntity<List<GetAllDiseaseResponse>> getAllDiseases() {
        List<GetAllDiseaseResponse> responses = Arrays.stream(Disease.values())
                .map(GetAllDiseaseResponse::new)
                .toList();
        return ResponseEntity.ok(responses);
    }
}
