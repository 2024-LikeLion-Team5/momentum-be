package com.momentum.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.momentum.domain.vo.PostType;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record IntegrationDoctorReviewSearchDto(
        long postId,
        String title,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.")
        LocalDateTime createdAt,
        String disease,
        String treatment,

        String doctor,
        int ageGroup,
        double rating,
        String content,
        long likeCount,
        long hits,
        PostType postType
) {

    public static IntegrationDoctorReviewSearchDto from(Object[] query) {
        return new IntegrationDoctorReviewSearchDto(
                ((Number) query[0]).longValue(),
                (String) query[1],
                ((Timestamp) query[2]).toLocalDateTime(),
                ((String) query[3]),
                ((String) query[4]),
                ((String) query[5]),
                ((Number) query[6]).intValue(),
                ((Number) query[7]).doubleValue(),
                ((String) query[8]),
                ((Number) query[9]).longValue(),
                ((Number) query[10]).longValue(),
                PostType.valueOf((String) query[11])
        );
    }
}
