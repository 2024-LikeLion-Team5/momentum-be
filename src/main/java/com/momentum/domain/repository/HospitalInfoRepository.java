package com.momentum.domain.repository;

import com.momentum.domain.entity.HospitalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HospitalInfoRepository extends JpaRepository<HospitalInfo, Long> {

    List<HospitalInfo> findByHospitalContainingOrAddressContaining(String hospital, String address);
}
