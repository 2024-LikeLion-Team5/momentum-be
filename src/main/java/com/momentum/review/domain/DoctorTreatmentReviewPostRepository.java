package com.momentum.review.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorTreatmentReviewPostRepository extends JpaRepository<DoctorTreatmentReviewPost, Long> {

    @Query("select count(*) from DoctorTreatmentReviewPost dtrp " +
            "WHERE LOWER(dtrp.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(dtrp.content) LIKE LOWER(CONCAT('%', :keyword, '%')) ")
    long countAllByTitleContainingAndContentContainingOrderByCreatedAtDesc(String keyword);

    List<DoctorTreatmentReviewPost> findAllByTitleContainingOrContentContainingOrderByCreatedAtDesc(
            String title,
            String content
    );

    List<DoctorTreatmentReviewPost> findAllByOrderByCreatedAtDesc();

    List<DoctorTreatmentReviewPost> findAllByDoctor(Doctor doctor);

    List<DoctorTreatmentReviewPost> findAllByDoctorNameContainingAndHospitalContainingOrderByCreatedAtDesc(
            String doctorNameContaining,
            String hospitalContaining
    );
}
