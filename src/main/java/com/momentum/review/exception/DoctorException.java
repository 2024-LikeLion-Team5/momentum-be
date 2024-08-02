package com.momentum.review.exception;

import com.momentum.global.exception.ExceptionType;
import lombok.Getter;

@Getter
public enum DoctorException implements ExceptionType {

    NON_EXISTENT_DOCTOR(600, "존재하지 않는 의사입니다."),
    ;

    private final int code;
    private final String message;

    DoctorException(final int code, final String message) {
        this.code = code;
        this.message = message;
    }
}
