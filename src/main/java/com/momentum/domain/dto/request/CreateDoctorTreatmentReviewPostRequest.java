package com.momentum.domain.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateDoctorTreatmentReviewPostRequest(

        @NotBlank(message = "병원명이 존재하지 않습니다.")
        String hospital,

        @NotBlank(message = "질환명이 존재하지 않습니다.")
        String disease,

        @NotBlank(message = "진단명이 존재하지 않습니다.")
        String treatment,

        @NotBlank(message = "연령대가 존재하지 않습니다.")
        int ageGroup,

        @NotBlank(message = "의사명이 존재하지 않습니다.")
        String doctor,

        @NotBlank(message = "평점이 존재하지 않습니다.")
        double rating,

        @NotBlank(message = "제목이 존재하지 않습니다.")
        String title,

        @NotBlank(message = "내용이 존재하지 않습니다.")
        String content

) {
}
