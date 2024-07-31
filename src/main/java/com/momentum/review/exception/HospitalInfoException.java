package com.momentum.review.exception;

import com.momentum.global.exception.ExceptionType;
import lombok.Getter;

@Getter
public enum HospitalInfoException implements ExceptionType {

    NON_EXISTENT_HOSPITAL_INFORMATION(400, "존재하지 않는 병원 정보입니다.");

    private final int code;
    private final String message;

    HospitalInfoException(final int code, final String message) {
        this.code = code;
        this.message = message;
    }
}
