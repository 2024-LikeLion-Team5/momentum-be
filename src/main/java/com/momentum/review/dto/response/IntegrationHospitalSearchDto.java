package com.momentum.review.dto.response;

import com.momentum.review.domain.HospitalInfo;

public record IntegrationHospitalSearchDto(
        long id,
        String hospital,
        String address,
        double averageFacilityRating,
        double averageAtmosphereRating,
        double averageEmployeeRating
) {

    public static IntegrationHospitalSearchDto from(HospitalInfo hospitalInfo) {
        return new IntegrationHospitalSearchDto(
                hospitalInfo.getId(),
                hospitalInfo.getHospital(),
                hospitalInfo.getAddress(),
                hospitalInfo.getAverageFacilityRating(),
                hospitalInfo.getAverageAtmosphereRating(),
                hospitalInfo.getAverageEmployeeRating()
        );
    }

    public static IntegrationHospitalSearchDto from(Object[] query) {
        return new IntegrationHospitalSearchDto(
                ((Number) query[0]).longValue(),
                (String) query[1],
                (String) query[2],
                ((Number) query[3]).doubleValue(),
                ((Number) query[4]).doubleValue(),
                ((Number) query[5]).doubleValue()
        );
    }
}
