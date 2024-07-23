package com.momentum.domain;

import com.momentum.domain.vo.Disease;
import com.momentum.domain.vo.PostType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public class SurgeryReviewPost extends Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Disease disease;

    private String surgery;

    @Builder
    private SurgeryReviewPost(
            final String title,
            final String content,
            final long hits,
            final long likes,
            final long dislikes,
            final Disease disease,
            final String surgery,
            final PostType postType
    ) {
        super(title, content, hits, likes, dislikes, postType);
        this.disease = disease;
        this.surgery = surgery;
    }
}
