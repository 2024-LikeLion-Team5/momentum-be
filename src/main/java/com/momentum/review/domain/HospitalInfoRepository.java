package com.momentum.review.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalInfoRepository extends JpaRepository<HospitalInfo, Long> {

    Page<HospitalInfo> findByHospitalContainingOrAddressContaining(String hospital, String address, Pageable pageable);

    HospitalInfo findByHospital(String hospital);

    long countByHospitalContainingOrAddressContaining(String hospital, String address);
}
