package com.momentum.application;

import com.momentum.domain.Comment;
import com.momentum.domain.CommentRepository;
import com.momentum.domain.ConcernPostRepository;
import com.momentum.domain.DailyPostRepository;
import com.momentum.domain.Post;
import com.momentum.domain.SurgeryReviewPostRepository;
import com.momentum.domain.vo.CommunityType;
import com.momentum.dto.request.comment.CreateCommentRequest;
import com.momentum.exception.CommunityPostException;
import com.momentum.global.exception.NotFoundException;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final ConcernPostRepository concernPostRepository;
    private final SurgeryReviewPostRepository surgeryReviewPostRepository;
    private final DailyPostRepository dailyPostRepository;
    private final CommentRepository commentRepository;

    public Long createComment(final Long postId, final CreateCommentRequest request) {
        CommunityType communityType = Arrays.stream(CommunityType.values())
                .filter(it -> it.name().equals(request.communityType()))
                .findAny()
                .orElseThrow(() -> new NotFoundException(CommunityPostException.NON_EXISTENT_COMMUNITY_TYPE));
        Post post = findPostBy(postId, communityType);

        Comment comment = Comment.builder()
                .post(post)
                .content(request.content())
                .build();
        return commentRepository.save(comment).getId();
    }

    private Post findPostBy(Long postId, CommunityType communityType) {
        return switch (communityType) {
            case CONCERN -> concernPostRepository.findById(postId)
                    .orElseThrow(() -> new NotFoundException(CommunityPostException.NON_EXISTENT_COMMUNITY_TYPE));
            case SURGERY_REVIEW -> surgeryReviewPostRepository.findById(postId)
                    .orElseThrow(() -> new NotFoundException(CommunityPostException.NON_EXISTENT_SURGERY_REVIEW_POST));
            case DAILY -> dailyPostRepository.findById(postId)
                    .orElseThrow(() -> new NotFoundException(CommunityPostException.NON_EXISTENT_DAILY_POST));
        };
    }
}
