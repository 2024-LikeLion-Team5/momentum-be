package com.momentum.review.domain;

import com.momentum.post.domain.Post;
import com.momentum.post.domain.vo.PostType;
import jakarta.persistence.Entity;
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
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class HospitalReviewPost extends Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_info_id")
    private HospitalInfo hospitalInfo;

    private String treatment;
    private String hospital;
    private double facilityRating;
    private double atmosphereRating;
    private double employeeRating;

    @Builder
    private HospitalReviewPost(
            final String title,
            final String content,
            final long hits,
            final long likes,
            final long dislikes,
            final PostType postType,
            final HospitalInfo hospitalInfo,
            final String treatment,
            final String hospital,
            final double facilityRating,
            final double atmosphereRating,
            final double employeeRating
    ) {
        super(title, content, hits, likes, dislikes, postType);
        this.hospitalInfo = hospitalInfo;
        this.treatment = treatment;
        this.hospital = hospital;
        this.facilityRating = facilityRating;
        this.atmosphereRating = atmosphereRating;
        this.employeeRating = employeeRating;
    }
}
