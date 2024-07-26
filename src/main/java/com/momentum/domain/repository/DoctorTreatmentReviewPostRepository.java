package com.momentum.domain.repository;

import com.momentum.domain.entity.DoctorTreatmentReviewPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorTreatmentReviewPostRepository extends JpaRepository<DoctorTreatmentReviewPost, Long> {

    @Query("SELECT drp FROM DoctorTreatmentReviewPost drp"
            + " ORDER BY drp.createdAt DESC")
    Page<DoctorTreatmentReviewPost> findAllByDiseaseAndOrderByCreatedAtDesc(Pageable pageable);

    Page<DoctorTreatmentReviewPost> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
