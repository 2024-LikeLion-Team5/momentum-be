package com.momentum.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.momentum.domain.vo.PostType;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record IntegrationHospitalSearchDto(
        long postId,
        String title,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.")
        LocalDateTime createdAt,

        long likeCount,
        long hits,
        PostType postType
) {

    public static IntegrationHospitalSearchDto from(Object[] query) {
        return new IntegrationHospitalSearchDto(
                ((Number) query[0]).longValue(),
                (String) query[1],
                ((Timestamp) query[2]).toLocalDateTime(),
                ((Number) query[3]).longValue(),
                ((Number) query[4]).longValue(),
                PostType.valueOf((String) query[5])
        );
    }
}
