package com.momentum.domain.repository;

import com.momentum.domain.entity.HospitalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalInfoRepository extends JpaRepository<HospitalInfo, Long> {
}
