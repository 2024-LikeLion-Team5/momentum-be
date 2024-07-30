package com.momentum.domain.dto.response;

import java.util.List;

public record GetAllHospitalInfoTotalResponse(
        long totalSearchedCount,
        List<HospitalInfosResponse> getHospitalInfoResponses
) {

    public static GetAllHospitalInfoTotalResponse of(
            long totalSearchedCount,
            List<IntegrationHospitalSearchDto> integrationHospitalSearchDtos
    ) {
        List<HospitalInfosResponse> hospitalInfosResponses = integrationHospitalSearchDtos.stream()
                .map(it -> new HospitalInfosResponse(
                        it.id(),
                        it.hospital(),
                        it.address(),
                        it.averageFacilityRating(),
                        it.averageAtmosphereRating(),
                        it.averageEmployeeRating()
                )).toList();

        return new GetAllHospitalInfoTotalResponse(totalSearchedCount, hospitalInfosResponses);
    }

    private record HospitalInfosResponse(
            long id,
            String hospital,
            String address,
            double averageFacilityRating,
            double averageAtmosphereRating,
            double averageEmployeeRating
    ) {
    }
}
