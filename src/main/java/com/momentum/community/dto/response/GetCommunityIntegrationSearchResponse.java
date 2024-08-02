package com.momentum.community.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.momentum.post.domain.vo.PostType;
import java.time.LocalDateTime;

public record GetCommunityIntegrationSearchResponse(
        long postId,
        PostType postType,
        String title,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.")
        LocalDateTime createdAt,

        long likeCount,
        long hits
) {

    public static GetCommunityIntegrationSearchResponse from(
            IntegrationCommunitySearchDto integrationCommunitySearchDto
    ) {
        return new GetCommunityIntegrationSearchResponse(
                integrationCommunitySearchDto.postId(),
                integrationCommunitySearchDto.postType(),
                integrationCommunitySearchDto.title(),
                integrationCommunitySearchDto.createdAt(),
                integrationCommunitySearchDto.likeCount(),
                integrationCommunitySearchDto.hits()
        );
    }
}
