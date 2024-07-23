package com.momentum.application;

import com.momentum.domain.DailyPost;
import com.momentum.domain.DailyPostRepository;
import com.momentum.domain.vo.PostType;
import com.momentum.dto.request.community.CreateDailyPostRequest;
import com.momentum.dto.response.community.GetAllDailyPostResponse;
import com.momentum.dto.response.community.GetDailyPostResponse;
import com.momentum.dto.response.community.GetDailyPostTotalResponse;
import com.momentum.exception.CommunityPostException;
import com.momentum.global.exception.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DailyPostService {

    private static final int INITIAL_PAGE_SIZE = 10;

    private final DailyPostRepository dailyPostRepository;

    public Long createDailyPost(final CreateDailyPostRequest request) {
        DailyPost dailyPost = DailyPost.builder()
                .title(request.title())
                .content(request.content())
                .hits(0)
                .likes(0)
                .dislikes(0)
                .postType(PostType.DAILY)
                .build();
        return dailyPostRepository.save(dailyPost).getId();
    }

    public GetDailyPostResponse getDailyPost(final Long postId) {
        DailyPost dailyPost = dailyPostRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(CommunityPostException.NON_EXISTENT_DAILY_POST));
        dailyPost.increaseHits();
        return GetDailyPostResponse.from(dailyPost);
    }

    @Transactional(readOnly = true)
    public List<GetAllDailyPostResponse> getAllDailyPosts(final int page) {
        Pageable pageable = PageRequest.of(page, INITIAL_PAGE_SIZE);
        return dailyPostRepository.findAllByOrderByCreatedAtDesc(pageable)
                .stream()
                .map(GetAllDailyPostResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<GetDailyPostTotalResponse> getDailyPostsTotal() {
        Pageable pageable = PageRequest.of(0, 6);
        return dailyPostRepository.findAllByOrderByCreatedAtDesc(pageable)
                .stream()
                .map(GetDailyPostTotalResponse::from)
                .toList();
    }
}
