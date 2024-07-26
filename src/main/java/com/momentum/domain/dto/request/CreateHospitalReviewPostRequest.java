package com.momentum.domain.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateHospitalReviewPostRequest (

        @NotBlank(message = "진단명이 존재하지 않습니다.")
        String treatment,

        @NotBlank(message = "병원명이 존재하지 않습니다.")
        String hospital,

        @NotBlank(message = "시설 평점이 존재하지 않습니다.")
        double facilityRating,

        @NotBlank(message = "분위기 평점이 존재하지 않습니다.")
        double atmosphereRating,

        @NotBlank(message = "직원 평점이 존재하지 않습니다.")
        double employeeRating,

        @NotBlank(message = "제목이 존재하지 않습니다.")
        String title,

        @NotBlank(message = "본문이 존재하지 않습니다.")
        String content
) {
}