package com.momentum.community.domain;

import com.momentum.community.domain.vo.Disease;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SurgeryReviewPostRepository extends JpaRepository<SurgeryReviewPost, Long> {

    @Query("SELECT srp FROM SurgeryReviewPost srp"
            + " WHERE (srp.disease IS NULL OR srp.disease = :disease)"
            + " ORDER BY srp.createdAt DESC")
    Page<SurgeryReviewPost> findAllByDiseaseAndOrderByCreatedAtDesc(
            @Param("disease") Disease disease,
            Pageable pageable
    );

    Page<SurgeryReviewPost> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
