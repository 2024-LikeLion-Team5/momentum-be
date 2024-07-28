package com.momentum.domain.dto.response;

import com.momentum.domain.entity.HospitalInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAllHospitalInfoTotalResponse {

    private String hospital;
    private String address;
    private double averageFacilityRating;
    private double averageAtmosphereRating;
    private double averageEmployeeRating;

    public static GetAllHospitalInfoTotalResponse of(HospitalInfo hospitalInfo) {
        return GetAllHospitalInfoTotalResponse.builder()
                .hospital(hospitalInfo.getHospital())
                .address(hospitalInfo.getAddress())
                .averageFacilityRating(hospitalInfo.getAverageFacilityRating())
                .averageAtmosphereRating(hospitalInfo.getAverageAtmosphereRating())
                .averageEmployeeRating(hospitalInfo.getAverageEmployeeRating())
                .build();
    }
}
