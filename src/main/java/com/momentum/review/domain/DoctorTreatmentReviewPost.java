package com.momentum.review.domain;

import com.momentum.community.domain.vo.Disease;
import com.momentum.post.domain.Post;
import com.momentum.post.domain.vo.PostType;
import com.momentum.review.domain.vo.AgeGroup;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class DoctorTreatmentReviewPost extends Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private String hospital;

    private Disease disease;

    private String treatment;

    @Enumerated(value = EnumType.STRING)
    private AgeGroup ageGroup;

    private String doctorName;

    private double rating;

    @Builder
    private DoctorTreatmentReviewPost(
            final String title,
            final String content,
            final long hits,
            final long likes,
            final long dislikes,
            final PostType postType,
            final Doctor doctor,
            final String hospital,
            final Disease disease,
            final String treatment,
            final AgeGroup ageGroup,
            final String doctorName,
            final double rating
    ) {
        super(title, content, hits, likes, dislikes, postType);
        this.doctor = doctor;
        this.hospital = hospital;
        this.disease = disease;
        this.treatment = treatment;
        this.ageGroup = ageGroup;
        this.doctorName = doctorName;
        this.rating = rating;
    }
}
