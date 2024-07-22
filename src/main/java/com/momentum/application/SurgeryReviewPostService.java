package com.momentum.application;

import com.momentum.domain.SurgeryReviewPost;
import com.momentum.domain.SurgeryReviewPostRepository;
import com.momentum.dto.request.CreateSurgeryReviewPostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SurgeryReviewPostService {

    private final SurgeryReviewPostRepository surgeryReviewPostRepository;


    public Long createSurgeryReviewPost(final CreateSurgeryReviewPostRequest request) {
        SurgeryReviewPost surgeryReviewPost = SurgeryReviewPost.builder()
                .title(request.title())
                .content(request.content())
                .hits(0)
                .likes(0)
                .dislikes(0)
                .disease(request.disease())
                .surgery(request.surgery())
                .build();
        return surgeryReviewPostRepository.save(surgeryReviewPost).getId();
    }
}
