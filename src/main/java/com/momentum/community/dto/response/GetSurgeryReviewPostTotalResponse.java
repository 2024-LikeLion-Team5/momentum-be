package com.momentum.community.dto.response;

import com.momentum.community.domain.SurgeryReviewPost;
import com.momentum.community.domain.vo.Disease;

public record GetSurgeryReviewPostTotalResponse(
        long postId,
        Disease disease,
        String title,
        String content
) {

    public static GetSurgeryReviewPostTotalResponse from(SurgeryReviewPost surgeryReviewPost) {
        return new GetSurgeryReviewPostTotalResponse(
                surgeryReviewPost.getId(),
                surgeryReviewPost.getDisease(),
                surgeryReviewPost.getTitle(),
                surgeryReviewPost.getContent()
        );
    }
}
