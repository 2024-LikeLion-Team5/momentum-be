package com.momentum.community.dto.request;

import com.momentum.community.domain.vo.Disease;
import jakarta.validation.constraints.NotBlank;

public record CreateConcernPostRequest(
        Disease disease,

        @NotBlank(message = "제목이 존재하지 않습니다.")
        String title,

        @NotBlank(message = "본문이 존재하지 않습니다.")
        String content
) {
}
