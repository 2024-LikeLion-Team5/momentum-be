package com.momentum.review.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class HospitalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hospital;
    private String address;
    private String operatingTime;
    private double averageFacilityRating;
    private double averageAtmosphereRating;
    private double averageEmployeeRating;

    public void updateRatings(
            long reviewCounts,
            double facilityRating,
            double atmosphereRating,
            double employeeRating
    ) {
        this.averageFacilityRating =
                ((this.averageFacilityRating * reviewCounts) + facilityRating) / (reviewCounts + 1);
        this.averageAtmosphereRating =
                ((this.averageAtmosphereRating * reviewCounts) + atmosphereRating) / (reviewCounts + 1);
        this.averageEmployeeRating =
                ((this.averageEmployeeRating * reviewCounts) + employeeRating) / (reviewCounts + 1);
    }
}
