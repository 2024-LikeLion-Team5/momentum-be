package com.momentum.review.domain;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalReviewPostRepository extends JpaRepository<HospitalReviewPost, Long> {

    List<HospitalReviewPost> findAllByOrderByCreatedAtDesc();

    long countAllByHospitalInfo(HospitalInfo hospitalInfo);
    
    Page<HospitalReviewPost> findAllByHospitalInfoId(Long hospitalInfoId, Pageable pageable);
}
