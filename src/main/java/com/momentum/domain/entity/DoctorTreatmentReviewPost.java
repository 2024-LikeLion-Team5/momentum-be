package com.momentum.domain.entity;

import com.momentum.domain.Post;
import com.momentum.domain.vo.PostType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class DoctorTreatmentReviewPost extends Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hospital;
    private String disease;
    private String treatment;
    private int ageGroup;
    private String doctor;
    private double rating;

    @Builder
    private DoctorTreatmentReviewPost(
            final String title,
            final String content,
            final long hits,
            final long likes,
            final long dislikes,
            final PostType postType,
            final String hospital,
            final String disease,
            final String treatment,
            final int ageGroup,
            final String doctor,
            final double rating
    ) {
        super(title, content, hits, likes, dislikes, postType);
        this.hospital = hospital;
        this.disease = disease;
        this.treatment = treatment;
        this.ageGroup = ageGroup;
        this.doctor = doctor;
        this.rating = rating;
    }
}