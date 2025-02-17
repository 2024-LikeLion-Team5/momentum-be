package com.momentum.community.dto.request;

import com.momentum.community.domain.vo.Disease;
import jakarta.validation.constraints.NotBlank;

public record CreateSurgeryReviewPostRequest(
        @NotBlank(message = "질환명이 존재하지 않습니다.")
        Disease disease,

        @NotBlank(message = "수술명이 존재하지 않습니다.")
        String surgery,

        @NotBlank(message = "제목이 존재하지 않습니다.")
        String title,

        @NotBlank(message = "본문이 존재하지 않습니다.")
        String content
) {
}
