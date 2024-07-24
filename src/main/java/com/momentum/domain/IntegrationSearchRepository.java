package com.momentum.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IntegrationSearchRepository extends JpaRepository<Post, Long> {

    @Query(value =
            "SELECT community.id, community.title, community.created_at, community.likes, community.hits, community.post_type "
                    +
                    "FROM (" +
                    "SELECT cp.id, p.title, p.content, p.created_at, p.likes, p.hits, p.post_type " +
                    "FROM concern_post cp " +
                    "JOIN post p ON cp.id = p.id " +
                    "UNION ALL " +
                    "SELECT srp.id, p.title, p.content, p.created_at, p.likes, p.hits, p.post_type " +
                    "FROM surgery_review_post srp " +
                    "JOIN post p ON srp.id = p.id " +
                    "UNION ALL " +
                    "SELECT dp.id, p.title, p.content, p.created_at, p.likes, p.hits, p.post_type " +
                    "FROM daily_post dp " +
                    "JOIN post p ON dp.id = p.id " +
                    ") community " +
                    "WHERE LOWER(community.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                    "OR LOWER(community.content) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                    "ORDER BY community.created_at DESC",
            countQuery = "SELECT COUNT(*) FROM (" +
                    "SELECT cp.id, p.title, p.content " +
                    "FROM concern_post cp " +
                    "JOIN post p ON cp.id = p.id " +
                    "UNION ALL " +
                    "SELECT srp.id, p.title, p.content " +
                    "FROM surgery_review_post srp " +
                    "JOIN post p ON srp.id = p.id " +
                    "UNION ALL " +
                    "SELECT dp.id, p.title, p.content " +
                    "FROM daily_post dp " +
                    "JOIN post p ON dp.id = p.id " +
                    ") community " +
                    "WHERE LOWER(community.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                    "OR LOWER(community.content) LIKE LOWER(CONCAT('%', :keyword, '%'))",
            nativeQuery = true)
    Page<Object[]> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM (" +
            "SELECT cp.id, p.title, p.content " +
            "FROM concern_post cp " +
            "JOIN post p ON cp.id = p.id " +
            "UNION ALL " +
            "SELECT srp.id, p.title, p.content " +
            "FROM surgery_review_post srp " +
            "JOIN post p ON srp.id = p.id " +
            "UNION ALL " +
            "SELECT dp.id, p.title, p.content " +
            "FROM daily_post dp " +
            "JOIN post p ON dp.id = p.id " +
            ") community " +
            "WHERE LOWER(community.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(community.content) LIKE LOWER(CONCAT('%', :keyword, '%'))",
            nativeQuery = true)
    long countAllByKeyword(String keyword);
}
