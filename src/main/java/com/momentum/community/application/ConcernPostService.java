package com.momentum.community.application;

import com.momentum.community.domain.ConcernPost;
import com.momentum.community.domain.ConcernPostRepository;
import com.momentum.community.domain.vo.Disease;
import com.momentum.community.dto.request.CreateConcernPostRequest;
import com.momentum.community.dto.response.GetAllConcernPostResponse;
import com.momentum.community.dto.response.GetConcernPostResponse;
import com.momentum.community.dto.response.GetConcernPostTotalResponse;
import com.momentum.community.exception.CommunityPostException;
import com.momentum.global.exception.NotFoundException;
import com.momentum.post.domain.vo.PostType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ConcernPostService {

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
                .postType(PostType.CONCERN)
                .build();
        return concernPostRepository.save(concernPost).getId();
    }

    public GetConcernPostResponse getConcernPost(Long postId) {
        ConcernPost concernPost = concernPostRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(CommunityPostException.NON_EXISTENT_CONCERN_POST));
        concernPost.increaseHits();
        return GetConcernPostResponse.from(concernPost);
    }

    @Transactional(readOnly = true)
    public List<GetAllConcernPostResponse> getAllConcernPosts(final String diseaseRequest, final int page) {
        final Pageable pageable = PageRequest.of(page, INITIAL_PAGE_SIZE);
        if (diseaseRequest == null) {
            return concernPostRepository.findAllByOrderByCreatedAtDesc(pageable)
                    .stream()
                    .map(GetAllConcernPostResponse::from)
                    .toList();
        }
        return concernPostRepository.findAllByDiseaseAndOrderByCreatedAtDesc(Disease.valueOf(diseaseRequest), pageable)
                .stream()
                .map(GetAllConcernPostResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<GetConcernPostTotalResponse> getConcernPostsTotal() {
        Pageable pageable = PageRequest.of(0, 6);
        return concernPostRepository.findAllByOrderByCreatedAtDesc(pageable)
                .stream()
                .map(GetConcernPostTotalResponse::from)
                .toList();
    }
}
