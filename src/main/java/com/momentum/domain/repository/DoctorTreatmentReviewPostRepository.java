package com.momentum.domain.repository;

import com.momentum.domain.entity.DoctorTreatmentReviewPost;
import com.momentum.domain.entity.HospitalReviewPost;
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

    Optional<DoctorTreatmentReviewPost> findAllByOrderByCreatedAtDesc();
}
