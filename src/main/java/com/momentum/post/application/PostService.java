package com.momentum.post.application;

import com.momentum.community.domain.ConcernPostRepository;
import com.momentum.community.domain.DailyPostRepository;
import com.momentum.community.domain.SurgeryReviewPostRepository;
import com.momentum.community.exception.CommunityPostException;
import com.momentum.global.exception.BadRequestException;
import com.momentum.global.exception.NotFoundException;
import com.momentum.post.domain.Post;
import com.momentum.post.domain.PostRepository;
import com.momentum.post.domain.vo.PostType;
import com.momentum.post.dto.request.UpdateDislikeRequest;
import com.momentum.post.dto.request.UpdateLikesRequest;
import com.momentum.review.domain.DoctorTreatmentReviewPostRepository;
import com.momentum.review.domain.HospitalReviewPostRepository;
import com.momentum.review.exception.ReviewPostException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ConcernPostRepository concernPostRepository;
    private final SurgeryReviewPostRepository surgeryReviewPostRepository;
    private final DailyPostRepository dailyPostRepository;
    private final DoctorTreatmentReviewPostRepository doctorTreatmentReviewPostRepository;
    private final HospitalReviewPostRepository hospitalReviewPostRepository;

    public void like(Long postId, UpdateLikesRequest request) {
        Post post = findPostBy(postId, request.postType());
        post.increaseLikes();
    }

    public void dislike(Long postId, UpdateDislikeRequest request) {
        Post post = findPostBy(postId, request.postType());
        post.increaseDislikes();
    }

    private Post findPostBy(Long postId, PostType postType) {
        return switch (postType) {
            case CONCERN -> concernPostRepository.findById(postId)
                    .orElseThrow(() -> new NotFoundException(CommunityPostException.NON_EXISTENT_COMMUNITY_TYPE));
            case SURGERY_REVIEW -> surgeryReviewPostRepository.findById(postId)
                    .orElseThrow(() -> new NotFoundException(CommunityPostException.NON_EXISTENT_SURGERY_REVIEW_POST));
            case DAILY -> dailyPostRepository.findById(postId)
                    .orElseThrow(() -> new NotFoundException(CommunityPostException.NON_EXISTENT_DAILY_POST));
            case DOCTOR_REVIEW -> doctorTreatmentReviewPostRepository.findById(postId)
                    .orElseThrow(() -> new NotFoundException(ReviewPostException.NON_EXISTENT_DOCTOR_REVIEW));
            case HOSPITAL_REVIEW -> hospitalReviewPostRepository.findById(postId)
                    .orElseThrow(() -> new NotFoundException(ReviewPostException.NON_EXISTENT_HOSPITAL_REVIEW));
            default -> throw new BadRequestException(CommunityPostException.NOT_CONTAINS_COMMUNITY);
        };
    }
}
