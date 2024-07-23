package com.momentum.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyPostRepository extends JpaRepository<DailyPost, Long> {
}
