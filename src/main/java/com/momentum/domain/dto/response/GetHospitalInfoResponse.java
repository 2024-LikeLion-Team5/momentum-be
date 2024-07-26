package com.momentum.domain.dto.response;

import com.momentum.domain.entity.HospitalInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetHospitalInfoResponse {

    private String hospital;
    private String address;
    private String operatingTime;
    private double facilityRating;
    private double atmosphereRating;
    private double employeeRating;

    public static GetHospitalInfoResponse of(HospitalInfo hospitalInfo) {
        return GetHospitalInfoResponse.builder()
                .hospital(hospitalInfo.getHospital())
                .address(hospitalInfo.getAddress())
                .operatingTime(hospitalInfo.getOperatingTime())
                .facilityRating(hospitalInfo.getFacilityRating())
                .atmosphereRating(hospitalInfo.getAtmosphereRating())
                .employeeRating(hospitalInfo.getEmployeeRating())
                .build();
    }
}
