package com.momentum.domain.repository;

import com.momentum.domain.entity.HospitalReviewPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalReviewPostRepository extends JpaRepository<HospitalReviewPost, Long> {

    @Query("SELECT hrp FROM HospitalReviewPost hrp"
            + " ORDER BY hrp.createdAt DESC")
    Page<HospitalReviewPost> findAllByDiseaseAndOrderByCreatedAtDesc(Pageable pageable);

    Page<HospitalReviewPost> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
