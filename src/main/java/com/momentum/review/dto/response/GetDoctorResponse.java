package com.momentum.review.dto.response;

import com.momentum.review.domain.Doctor;

public record GetDoctorResponse(
        Long doctorId,
        String doctorName
) {

    public static GetDoctorResponse from(Doctor doctor) {
        return new GetDoctorResponse(doctor.getId(), doctor.getName());
    }
}
