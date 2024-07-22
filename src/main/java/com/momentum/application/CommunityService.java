package com.momentum.application;

import com.momentum.domain.ConcernPost;
import com.momentum.domain.ConcernPostRepository;
import com.momentum.dto.request.CreateConcernPostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final ConcernPostRepository concernPostRepository;

    public Long createConcernPost(CreateConcernPostRequest request) {
        ConcernPost concernPost = ConcernPost.builder()
                .title(request.title())
                .content(request.content())
                .disease(request.disease())
                .hits(0)
                .likes(0)
                .dislikes(0)
                .build();
        return concernPostRepository.save(concernPost).getId();
    }
}
