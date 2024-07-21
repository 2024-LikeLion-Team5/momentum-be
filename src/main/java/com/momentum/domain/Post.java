package com.momentum.domain;

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
public abstract class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private long hits;

    private long like;

    private long dislike;

    @Builder
    private Post(
            final String title,
            final String content,
            final long hits,
            final long like,
            final long dislike
    ) {
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.like = like;
        this.dislike = dislike;
    }
}
