package com.momentum.review.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorTreatmentReviewPostRepository extends JpaRepository<DoctorTreatmentReviewPost, Long> {

    Optional<DoctorTreatmentReviewPost> findAllByOrderByCreatedAtDesc();
}
