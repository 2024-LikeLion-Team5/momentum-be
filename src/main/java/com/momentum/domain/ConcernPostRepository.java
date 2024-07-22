package com.momentum.domain;

import com.momentum.domain.vo.Disease;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConcernPostRepository extends JpaRepository<ConcernPost, Long> {

    @Query("SELECT cp FROM ConcernPost cp"
            + " WHERE (cp.disease IS NULL OR cp.disease = :disease)"
            + " ORDER BY cp.createdAt DESC")
    Page<ConcernPost> findAllByDiseaseAndOrderByCreatedAtDesc(@Param("disease") Disease disease, Pageable pageable);
}
