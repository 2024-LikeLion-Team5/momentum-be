package com.momentum.review.exception;

import com.momentum.global.exception.ExceptionType;
import lombok.Getter;

@Getter
public enum ReviewPostException implements ExceptionType {

    NON_EXISTENT_DOCTOR_REVIEW(500, "존재하지 않는 의사 후기입니다."),
    NON_EXISTENT_HOSPITAL_REVIEW(501, "존재하지 않는 병원 후기입니다."),
    ;

    private final int code;
    private final String message;

    ReviewPostException(final int code, final String message) {
        this.code = code;
        this.message = message;
    }
}
