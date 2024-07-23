package com.momentum.application;

import com.momentum.domain.IntegrationSearchRepository;
import com.momentum.dto.response.community.GetCommunityIntegrationSearchResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IntegrationSearchService {

    private static final int INITIAL_PAGE_SIZE = 10;

    private final IntegrationSearchRepository integrationSearchRepository;

    public List<GetCommunityIntegrationSearchResponse> getCommunityPosts(final String keyword, final int page) {
        Pageable pageable = PageRequest.of(page, INITIAL_PAGE_SIZE);
        return integrationSearchRepository.findAllByKeyword(keyword, pageable)
                .stream()
                .map(GetCommunityIntegrationSearchResponse::from)
                .toList();
    }
}
