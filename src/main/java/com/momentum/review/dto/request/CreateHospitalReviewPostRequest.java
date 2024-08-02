package com.momentum.review.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateHospitalReviewPostRequest(

        @NotBlank(message = "진단명이 존재하지 않습니다.")
        String treatment,

        @NotBlank(message = "병원명이 존재하지 않습니다.")
        String hospital,

        double facilityRating,

        double atmosphereRating,

        double employeeRating,

        @NotBlank(message = "제목이 존재하지 않습니다.")
        String title,

        @NotBlank(message = "본문이 존재하지 않습니다.")
        String content
) {
}
