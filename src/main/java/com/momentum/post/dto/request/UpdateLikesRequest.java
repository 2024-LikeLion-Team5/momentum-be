package com.momentum.post.dto.request;

import com.momentum.post.domain.vo.PostType;

public record UpdateLikesRequest(
        PostType postType
) {
}
