package com.momentum.review.dto.request;

import com.momentum.community.domain.vo.Disease;
import com.momentum.review.domain.vo.AgeGroup;
import jakarta.validation.constraints.NotBlank;

public record CreateDoctorTreatmentReviewPostRequest(

        @NotBlank(message = "병원명이 존재하지 않습니다.")
        String hospital,

        Disease disease,

        @NotBlank(message = "진단명이 존재하지 않습니다.")
        String treatment,

        AgeGroup ageGroup,

        @NotBlank(message = "의사명이 존재하지 않습니다.")
        String doctor,

        double rating,

        @NotBlank(message = "제목이 존재하지 않습니다.")
        String title,

        @NotBlank(message = "내용이 존재하지 않습니다.")
        String content

) {
}
