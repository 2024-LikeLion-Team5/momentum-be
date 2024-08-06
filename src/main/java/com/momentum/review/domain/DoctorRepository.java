package com.momentum.review.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByNameAndHospitalInfo(String name, HospitalInfo hospitalInfo);

    List<Doctor> findAllByHospitalInfo(HospitalInfo hospitalInfo);

    Doctor findHospitalInfo(HospitalInfo hospitalInfo);
}
