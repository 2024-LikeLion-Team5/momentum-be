package com.momentum.review.domain;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalInfoRepository extends JpaRepository<HospitalInfo, Long> {

    Page<HospitalInfo> findByHospitalContainingOrAddressContaining(String hospital, String address, Pageable pageable);

    long countByHospitalContainingOrAddressContaining(String hospital, String address);

    Optional<HospitalInfo> findByHospital(String hospital);
}
