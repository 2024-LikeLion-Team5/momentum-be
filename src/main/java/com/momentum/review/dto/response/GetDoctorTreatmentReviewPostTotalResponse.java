package com.momentum.review.dto.response;

import com.momentum.community.domain.vo.Disease;
import com.momentum.review.domain.DoctorTreatmentReviewPost;
import com.momentum.review.domain.vo.AgeGroup;
import java.util.List;

public record GetDoctorTreatmentReviewPostTotalResponse(
        long totalSearchedCount,
        List<DoctorTreatmentReviewPostResponse> doctorTreatmentReviewPostRespons
) {

    public static GetDoctorTreatmentReviewPostTotalResponse of(
            long totalSearchedCount,
            List<DoctorTreatmentReviewPost> doctorTreatmentReviewPosts
    ) {
        List<DoctorTreatmentReviewPostResponse> doctorTreatmentReviewPostResponse =
                doctorTreatmentReviewPosts.stream()
                        .map(doctorTreatmentReviewPost -> new DoctorTreatmentReviewPostResponse(
                                        doctorTreatmentReviewPost.getId(),
                                        doctorTreatmentReviewPost.getDoctorName(),
                                        doctorTreatmentReviewPost.getHospital(),
                                        doctorTreatmentReviewPost.getRating(),
                                        doctorTreatmentReviewPost.getDisease(),
                                        doctorTreatmentReviewPost.getTreatment(),
                                        doctorTreatmentReviewPost.getAgeGroup(),
                                        doctorTreatmentReviewPost.getTitle(),
                                        doctorTreatmentReviewPost.getContent()
                                )
                        ).toList();
        return new GetDoctorTreatmentReviewPostTotalResponse(totalSearchedCount, doctorTreatmentReviewPostResponse);
    }

    private record DoctorTreatmentReviewPostResponse(
            Long postId,
            String doctor,
            String hospital,
            double rating,
            Disease disease,
            String treatment,
            AgeGroup ageGroup,
            String title,
            String content
    ) {
    }
}
