package com.momentum.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public abstract class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private long hits;

    private long likes;

    private long dislikes;

    private boolean isNotice;

    protected Post(
            final String title,
            final String content,
            final long hits,
            final long likes,
            final long dislikes
    ) {
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.likes = likes;
        this.dislikes = dislikes;
        this.isNotice = false;
    }
}
