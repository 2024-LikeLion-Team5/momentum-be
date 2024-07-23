package com.momentum.domain;

import com.momentum.dto.response.community.IntegrationCommunitySearchDto;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IntegrationSearchRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT new com.momentum.dto.response.community.IntegrationCommunitySearchDto(" +
            "community.id, community.title, community.created_at, community.likes, community.hits, community.post_type) "
            + "FROM (" +
            "SELECT cp.id, p.title, p.content, p.created_at, p.likes, p.hits, p.post_type " +
            "FROM ConcernPost cp " +
            "JOIN Post p ON cp.post_id = p.id " +
            "UNION ALL " +
            "SELECT srp.id, p.title, p.content, p.created_at, p.likes, p.hits, p.post_type " +
            "FROM SurgeryReviewPost srp " +
            "JOIN Post p ON srp.post_id = p.id " +
            "UNION ALL " +
            "SELECT dp.id, p.title, p.content, p.created_at, p.likes, p.hits, p.post_type " +
            "FROM DailyPost dp " +
            "JOIN Post p ON dp.post_id = p.id " +
            ") community " +
            "WHERE LOWER(community.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(community.content) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "ORDER BY community.created_at DESC",
            countQuery = "SELECT COUNT(*) " +
                    "FROM (" +
                    "SELECT cp.id, p.title, p.content, p.created_at, p.likes, p.hits, p.post_type " +
                    "FROM ConcernPost cp " +
                    "JOIN Post p ON cp.post_id = p.id " +
                    "UNION ALL " +
                    "SELECT srp.id, p.title, p.content, p.created_at, p.likes, p.hits, p.post_type " +
                    "FROM SurgeryReviewPost srp " +
                    "JOIN Post p ON srp.post_id = p.id " +
                    "UNION ALL " +
                    "SELECT dp.id, p.title, p.content, p.created_at, p.likes, p.hits, p.post_type " +
                    "FROM DailyPost dp " +
                    "JOIN Post p ON dp.post_id = p.id " +
                    ") community " +
                    "WHERE LOWER(community.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                    "OR LOWER(community.content) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                    "ORDER BY community.created_at DESC",
            nativeQuery = true)
    List<IntegrationCommunitySearchDto> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "SELECT COUNT(*) " +
            "FROM (" +
            "SELECT cp.id, p.title, p.content, p.created_at, p.likes, p.hits, p.post_type " +
            "FROM ConcernPost cp " +
            "JOIN Post p ON cp.post_id = p.id " +
            "UNION ALL " +
            "SELECT srp.id, p.title, p.content, p.created_at, p.likes, p.hits, p.post_type " +
            "FROM SurgeryReviewPost srp " +
            "JOIN Post p ON srp.post_id = p.id " +
            "UNION ALL " +
            "SELECT dp.id, p.title, p.content, p.created_at, p.likes, p.hits, p.post_type " +
            "FROM DailyPost dp " +
            "JOIN Post p ON dp.post_id = p.id " +
            ") community " +
            "WHERE LOWER(combined.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(combined.content) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "ORDER BY combined.created_at DESC",
            nativeQuery = true)
    long countAllByKeyword(String keyword);
}
