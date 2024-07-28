package com.momentum.domain.dto.response;

import com.momentum.domain.entity.HospitalInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAllHospitalInfoTotalResponse {

    private String hospital;
    private String address;
    private double facilityRating;
    private double atmosphereRating;
    private double employeeRating;

    public static GetAllHospitalInfoTotalResponse of(HospitalInfo hospitalInfo) {
        return GetAllHospitalInfoTotalResponse.builder()
                .hospital(hospitalInfo.getHospital())
                .address(hospitalInfo.getAddress())
                .facilityRating(hospitalInfo.getFacilityRating())
                .atmosphereRating(hospitalInfo.getAtmosphereRating())
                .employeeRating(hospitalInfo.getEmployeeRating())
                .build();
    }
}
