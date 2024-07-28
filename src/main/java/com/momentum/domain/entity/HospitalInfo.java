package com.momentum.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class HospitalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hospitalId;

    private String hospital;
    private String address;
    private String operatingTime;
    private double facilityRating;
    private double atmosphereRating;
    private double employeeRating;
}
