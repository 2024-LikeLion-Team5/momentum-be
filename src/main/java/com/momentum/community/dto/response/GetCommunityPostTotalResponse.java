package com.momentum.community.dto.response;

import com.momentum.post.domain.vo.PostType;
import java.util.List;

public record GetCommunityPostTotalResponse(
        long totalSearchedCount,
        List<CommunityPostsResponse> communityPosts
) {

    public static GetCommunityPostTotalResponse of(
            long totalSearchedCount,
            List<IntegrationCommunitySearchDto> integrationCommunitySearchDtos
    ) {
        List<CommunityPostsResponse> communityPostsResponses = integrationCommunitySearchDtos.stream()
                .map(it -> new CommunityPostsResponse(
                        it.postId(),
                        it.postType(),
                        it.title()
                )).toList();
        return new GetCommunityPostTotalResponse(totalSearchedCount, communityPostsResponses);
    }

    private record CommunityPostsResponse(
            long postId,
            PostType postType,
            String title
    ) {
    }
}
