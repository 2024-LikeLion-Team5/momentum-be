package com.momentum.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyPostRepository extends JpaRepository<DailyPost, Long> {

    Page<DailyPost> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
