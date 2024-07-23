package com.momentum.application;

import com.momentum.domain.DailyPost;
import com.momentum.domain.DailyPostRepository;
import com.momentum.dto.request.community.CreateDailyPostRequest;
import com.momentum.dto.response.community.GetDailyPostResponse;
import com.momentum.exception.CommunityPostException;
import com.momentum.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DailyPostService {

    private final DailyPostRepository dailyPostRepository;

    public Long createDailyPost(final CreateDailyPostRequest request) {
        DailyPost dailyPost = DailyPost.builder()
                .title(request.title())
                .content(request.content())
                .hits(0)
                .likes(0)
                .dislikes(0)
                .build();
        return dailyPostRepository.save(dailyPost).getId();
    }

    public GetDailyPostResponse getDailyPost(final Long postId) {
        DailyPost dailyPost = dailyPostRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(CommunityPostException.NON_EXISTENT_DAILY_POST));
        dailyPost.increaseHits();
        return GetDailyPostResponse.from(dailyPost);
    }
}
