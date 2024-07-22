package com.momentum.dto.response;

import com.momentum.domain.ConcernPost;
import com.momentum.domain.vo.Disease;

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
