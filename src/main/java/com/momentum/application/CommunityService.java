package com.momentum.application;

import com.momentum.domain.ConcernPost;
import com.momentum.domain.ConcernPostRepository;
import com.momentum.domain.vo.Disease;
import com.momentum.dto.request.CreateConcernPostRequest;
import com.momentum.dto.response.GetAllConcernPostResponse;
import com.momentum.dto.response.GetConcernPostResponse;
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
public class CommunityService {

    private static final int INITIAL_PAGE_SIZE = 10;

    private final ConcernPostRepository concernPostRepository;

    public Long createConcernPost(CreateConcernPostRequest request) {
        ConcernPost concernPost = ConcernPost.builder()
                .title(request.title())
                .content(request.content())
                .disease(request.disease())
                .hits(0)
                .likes(0)
                .dislikes(0)
                .build();
        return concernPostRepository.save(concernPost).getId();
    }

    @Transactional(readOnly = true)
    public GetConcernPostResponse getConcernPost(Long postId) {
        ConcernPost concernPost = concernPostRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(CommunityPostException.NON_EXISTENT_CONCERN_POST));
        return GetConcernPostResponse.from(concernPost);
    }

    public List<GetAllConcernPostResponse> getAllConcernPosts(final String diseaseRequest, final int page) {
        final Pageable pageable = PageRequest.of(page, INITIAL_PAGE_SIZE);
        return concernPostRepository.findAllByDiseaseAndOrderByCreatedAtDesc(Disease.valueOf(diseaseRequest), pageable)
                .stream()
                .map(GetAllConcernPostResponse::from)
                .toList();
    }
}