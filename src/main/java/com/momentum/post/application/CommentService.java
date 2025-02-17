package com.momentum.post.application;

import com.momentum.post.domain.Comment;
import com.momentum.post.domain.CommentRepository;
import com.momentum.community.domain.ConcernPostRepository;
import com.momentum.community.domain.DailyPostRepository;
import com.momentum.post.domain.Post;
import com.momentum.community.domain.SurgeryReviewPostRepository;
import com.momentum.post.domain.vo.PostType;
import com.momentum.post.dto.request.CreateCommentRequest;
import com.momentum.post.dto.response.GetAllCommentResponses;
import com.momentum.community.exception.CommunityPostException;
import com.momentum.global.exception.BadRequestException;
import com.momentum.global.exception.NotFoundException;
import java.util.List;
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
        PostType postType = PostType.getCommunityType(request.communityType());
        Post post = findPostBy(postId, postType);

        Comment comment = Comment.builder()
                .post(post)
                .content(request.content())
                .build();
        return commentRepository.save(comment).getId();
    }

    @Transactional(readOnly = true)
    public GetAllCommentResponses getAllComments(final Long postId, final String communityTypeRequest) {
        PostType postType = PostType.getCommunityType(communityTypeRequest);
        Post post = findPostBy(postId, postType);
        List<Comment> comments = commentRepository.findAllByPost(post);
        return GetAllCommentResponses.of(comments.size(), comments);
    }

    private Post findPostBy(Long postId, PostType postType) {
        return switch (postType) {
            case CONCERN -> concernPostRepository.findById(postId)
                    .orElseThrow(() -> new NotFoundException(CommunityPostException.NON_EXISTENT_COMMUNITY_TYPE));
            case SURGERY_REVIEW -> surgeryReviewPostRepository.findById(postId)
                    .orElseThrow(() -> new NotFoundException(CommunityPostException.NON_EXISTENT_SURGERY_REVIEW_POST));
            case DAILY -> dailyPostRepository.findById(postId)
                    .orElseThrow(() -> new NotFoundException(CommunityPostException.NON_EXISTENT_DAILY_POST));
            default -> throw new BadRequestException(CommunityPostException.NOT_CONTAINS_COMMUNITY);
        };
    }
}
