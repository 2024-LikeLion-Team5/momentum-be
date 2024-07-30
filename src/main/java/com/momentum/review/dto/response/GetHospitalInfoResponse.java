package com.momentum.review.dto.response;

import com.momentum.review.domain.HospitalInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetHospitalInfoResponse {

    private String hospital;
    private String address;
    private String operatingTime;
    private double averageFacilityRating;
    private double averageAtmosphereRating;
    private double averageEmployeeRating;

    public static GetHospitalInfoResponse of(HospitalInfo hospitalInfo) {
        return GetHospitalInfoResponse.builder()
                .hospital(hospitalInfo.getHospital())
                .address(hospitalInfo.getAddress())
                .operatingTime(hospitalInfo.getOperatingTime())
                .averageFacilityRating(hospitalInfo.getAverageFacilityRating())
                .averageAtmosphereRating(hospitalInfo.getAverageAtmosphereRating())
                .averageEmployeeRating(hospitalInfo.getAverageEmployeeRating())
                .build();
    }
}
