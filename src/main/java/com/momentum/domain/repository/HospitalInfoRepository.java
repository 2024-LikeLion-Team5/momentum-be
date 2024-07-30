package com.momentum.domain.repository;

import com.momentum.domain.entity.HospitalInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalInfoRepository extends JpaRepository<HospitalInfo, Long> {

//    Page<HospitalInfo> findByHospitalContainingOrAddressContaining(String hospital, String address, Pageable pageable);

    List<HospitalInfo> findByHospitalContainingOrAddressContaining(String hospital, String address, Pageable pageable);

    HospitalInfo findByHospital(String hospital);

    long countByHospitalContainingOrAddressContaining(String hospital, String address);
}