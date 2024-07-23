package com.momentum.application;

import com.momentum.domain.ConcernPostRepository;
import com.momentum.domain.DailyPostRepository;
import com.momentum.domain.IntegrationSearchRepository;
import com.momentum.domain.SurgeryReviewPostRepository;
import com.momentum.dto.response.community.GetCommunityIntegrationSearchResponse;
import com.momentum.dto.response.community.GetCommunityPostTotalResponse;
import com.momentum.dto.response.community.IntegrationCommunitySearchDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IntegrationSearchService {

    private static final int INITIAL_PAGE_SIZE = 10;

    private final IntegrationSearchRepository integrationSearchRepository;
    private final ConcernPostRepository concernPostRepository;
    private final SurgeryReviewPostRepository surgeryReviewPostRepository;
    private final DailyPostRepository dailyPostRepository;

    public List<GetCommunityIntegrationSearchResponse> getCommunityPosts(final String keyword, final int page) {
        Pageable pageable = PageRequest.of(page, INITIAL_PAGE_SIZE);
        return integrationSearchRepository.findAllByKeyword(keyword, pageable)
                .stream()
                .map(GetCommunityIntegrationSearchResponse::from)
                .toList();
    }

    public GetCommunityPostTotalResponse getCommunityPostsTotal(final String keyword) {
        Pageable pageable = PageRequest.of(0, 6);
        long totalSearchedCount = integrationSearchRepository.countAllByKeyword(keyword);
        List<IntegrationCommunitySearchDto> integrationCommunitySearchDtos
                = integrationSearchRepository.findAllByKeyword(keyword, pageable);
        return GetCommunityPostTotalResponse.of(totalSearchedCount, integrationCommunitySearchDtos);
    }
}
