package com.momentum.review.dto.response;

public record IntegrationHospitalSearchDto(
        long id,
        String hospital,
        String address,
        double averageFacilityRating,
        double averageAtmosphereRating,
        double averageEmployeeRating
) {

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
