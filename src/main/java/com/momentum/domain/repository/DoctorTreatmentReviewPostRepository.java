package com.momentum.domain.repository;

import com.momentum.domain.entity.DoctorTreatmentReviewPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorTreatmentReviewPostRepository extends JpaRepository<DoctorTreatmentReviewPost, Long> {

//    @Query("SELECT drp FROM DoctorTreatmentReviewPost drp"
//            + " WHERE (drp.keyword IS NULL OR drp.keyword = :keyword)"
//            + " ORDER BY drp.createdAt DESC")
//    Page<DoctorTreatmentReviewPost> findByHospitalOrLocationAndOrderByCreatedAtDesc(@Param("keyword") String keyword);

//    Optional<DoctorTreatmentReviewPost> findByKeywordIsNullOrKeywordOrderByCreatedAtDesc(String keyword);

    List<DoctorTreatmentReviewPost> findByHospitalContainingOrDoctorContainingOrderByCreatedAtDesc(String hospital, String doctor);

    Page<DoctorTreatmentReviewPost> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
