package com.momentum.review.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorTreatmentReviewPostRepository extends JpaRepository<DoctorTreatmentReviewPost, Long> {

    @Query("select count(*) from DoctorTreatmentReviewPost dtrp " +
            "WHERE LOWER(dtrp.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(dtrp.content) LIKE LOWER(CONCAT('%', :keyword, '%')) ")
    long countAllByTitleAndContentContainingOrderByCreatedAtDesc(String keyword);

    List<DoctorTreatmentReviewPost> findAllByTitleAndContentContainingOrderByCreatedAtDesc(String keyword);
}
