package com.momentum.dto.response.community;

import com.momentum.domain.SurgeryReviewPost;
import com.momentum.domain.vo.Disease;

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
