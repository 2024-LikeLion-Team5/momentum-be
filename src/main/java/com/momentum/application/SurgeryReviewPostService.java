package com.momentum.application;

import com.momentum.domain.SurgeryReviewPost;
import com.momentum.domain.SurgeryReviewPostRepository;
import com.momentum.dto.request.CreateSurgeryReviewPostRequest;
import com.momentum.dto.response.GetSurgeryReviewPostResponse;
import com.momentum.exception.CommunityPostException;
import com.momentum.global.exception.NotFoundException;
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

    public GetSurgeryReviewPostResponse getSurgeryReviewPost(final Long postId) {
        SurgeryReviewPost surgeryReviewPost = surgeryReviewPostRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(CommunityPostException.NON_EXISTENT_SURGERY_REVIEW_POST));
        surgeryReviewPost.increaseHits();
        return GetSurgeryReviewPostResponse.from(surgeryReviewPost);
    }
}
