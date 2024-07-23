package com.momentum.dto.response.community;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.momentum.domain.vo.PostType;
import java.time.LocalDateTime;

public record IntegrationCommunitySearchDto(
        long postId,
        String title,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.")
        LocalDateTime createdAt,

        long likeCount,
        long hits,
        PostType postType
) {
}
