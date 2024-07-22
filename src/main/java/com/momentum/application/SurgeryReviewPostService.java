package com.momentum.application;

import com.momentum.domain.SurgeryReviewPost;
import com.momentum.domain.SurgeryReviewPostRepository;
import com.momentum.domain.vo.Disease;
import com.momentum.dto.request.CreateSurgeryReviewPostRequest;
import com.momentum.dto.response.GetAllSurgeryReviewPostResponse;
import com.momentum.dto.response.GetSurgeryReviewPostResponse;
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
public class SurgeryReviewPostService {

    private static final int INITIAL_PAGE_SIZE = 10;

    private final SurgeryReviewPostRepository surgeryReviewPostRepository;

    public Long createSurgeryReviewPost(final CreateSurgeryReviewPostRequest request) {
        SurgeryReviewPost surgeryReviewPost = SurgeryReviewPost.builder()
                .title(request.title())
                .content(request.content())
                .hits(0)
                .likes(0)
                .dislikes(0)
                .disease(request.disease())
                .surgery(request.surgery())
                .build();
        return surgeryReviewPostRepository.save(surgeryReviewPost).getId();
    }

    public GetSurgeryReviewPostResponse getSurgeryReviewPost(final Long postId) {
        SurgeryReviewPost surgeryReviewPost = surgeryReviewPostRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(CommunityPostException.NON_EXISTENT_SURGERY_REVIEW_POST));
        surgeryReviewPost.increaseHits();
        return GetSurgeryReviewPostResponse.from(surgeryReviewPost);
    }

    @Transactional(readOnly = true)
    public List<GetAllSurgeryReviewPostResponse> getAllSurgeryReviewPosts(final String disease, final int page) {
        Pageable pageable = PageRequest.of(page, INITIAL_PAGE_SIZE);
        return surgeryReviewPostRepository.findAllByDiseaseAndOrderByCreatedAtDesc(Disease.valueOf(disease), pageable)
                .stream()
                .map(GetAllSurgeryReviewPostResponse::from)
                .toList();
    }
}