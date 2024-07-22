package com.momentum.domain;

import com.momentum.domain.vo.Disease;
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
public class ConcernPost extends Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Disease disease;

    @Builder
    private ConcernPost(
            final String title,
            final String content,
            final long hits,
            final long likes,
            final long dislikes,
            final Disease disease
    ) {
        super(title, content, hits, likes, dislikes);
        this.disease = disease;
    }
}