package com.momentum.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.momentum.domain.entity.DoctorTreatmentReviewPost;
import com.momentum.domain.vo.PostType;
import com.momentum.dto.response.community.GetCommunityPostTotalResponse;
import com.momentum.dto.response.community.IntegrationCommunitySearchDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class GetDoctorTreatmentReviewPostTotalResponse {

    private Long postId;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.")
    private LocalDateTime createdAt;
    private String disease;
    private String treatment;
    private String doctor;
    private int ageGroup;
    private double rating;
    private String content;

    private long totalSearchedCount;
    private List<DoctorTreatmentReviewPostsResponse> doctorTreatmentReviewPostsResponses;

    public static GetDoctorTreatmentReviewPostTotalResponse of(DoctorTreatmentReviewPost doctorTreatmentReviewPost) {
        return GetDoctorTreatmentReviewPostTotalResponse.builder()
                .postId(doctorTreatmentReviewPost.getId())
                .title(doctorTreatmentReviewPost.getTitle())
                .createdAt(doctorTreatmentReviewPost.getCreatedAt())
                .disease(doctorTreatmentReviewPost.getDisease())
                .treatment(doctorTreatmentReviewPost.getTreatment())
                .doctor(doctorTreatmentReviewPost.getDoctor())
                .ageGroup(doctorTreatmentReviewPost.getAgeGroup())
                .rating(doctorTreatmentReviewPost.getRating())
                .content(doctorTreatmentReviewPost.getContent())
                .build();
    }

    public static GetDoctorTreatmentReviewPostTotalResponse of(
            long totalSearchedCount,
            List<IntegrationDoctorReviewSearchDto> integrationDoctorReviewSearchDtos
    ) {
        List<DoctorTreatmentReviewPostsResponse> doctorTreatmentReviewPostsResponses = integrationDoctorReviewSearchDtos.stream()
                .map(it -> new DoctorTreatmentReviewPostsResponse(
                        it.postId(),
                        it.title(),
                        it.createdAt(),
                        it.disease(),
                        it.treatment(),
                        it.doctor(),
                        it.ageGroup(),
                        it.rating(),
                        it.content()
                )).toList();

        return GetDoctorTreatmentReviewPostTotalResponse.builder()
                .totalSearchedCount(totalSearchedCount)
                .doctorTreatmentReviewPostsResponses(doctorTreatmentReviewPostsResponses)
                .build();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DoctorTreatmentReviewPostResponse {

        private Long id;
        private String title;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.")
        private LocalDateTime createdAt;
        private String disease;
        private String treatment;
        private String doctor;
        private int ageGroup;
        private double rating;
        private String content;

        public static DoctorTreatmentReviewPostResponse of(DoctorTreatmentReviewPost doctorTreatmentReviewPost) {
            return DoctorTreatmentReviewPostResponse.builder()
                    .id(doctorTreatmentReviewPost.getId())
                    .title(doctorTreatmentReviewPost.getTitle())
                    .createdAt(doctorTreatmentReviewPost.getCreatedAt())
                    .disease(doctorTreatmentReviewPost.getDisease())
                    .treatment(doctorTreatmentReviewPost.getTreatment())
                    .doctor(doctorTreatmentReviewPost.getDoctor())
                    .ageGroup(doctorTreatmentReviewPost.getAgeGroup())
                    .rating(doctorTreatmentReviewPost.getRating())
                    .content(doctorTreatmentReviewPost.getContent())
                    .build();
        }
    }

    public record DoctorTreatmentReviewPostsResponse(
            Long id,
            String title,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd.")
            LocalDateTime createdAt,
            String disease,
            String treatment,
            String doctor,
            int ageGroup,
            double rating,
            String content
    ) {
    }
}
