package com.momentum.community.dto.response;

import com.momentum.community.domain.ConcernPost;
import com.momentum.community.domain.vo.Disease;

public record GetConcernPostTotalResponse(
        long postId,
        Disease disease,
        String title
) {

    public static GetConcernPostTotalResponse from(ConcernPost concernPost) {
        return new GetConcernPostTotalResponse(
                concernPost.getId(),
                concernPost.getDisease(),
                concernPost.getTitle()
        );
    }
}
