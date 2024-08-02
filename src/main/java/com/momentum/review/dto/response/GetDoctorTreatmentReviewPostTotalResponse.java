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
                        .map(it -> new DoctorTreatmentReviewPostResponse(
                                it.getId(),
                                it.getDoctorName(),
                                it.getHospital(),
                                it.getRating(),
                                it.getDisease(),
                                it.getTreatment(),
                                it.getAgeGroup(),
                                it.getTitle(),
                                it.getContent()
                        )).toList();
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
