package com.momentum.exception;

import com.momentum.global.exception.ExceptionType;
import lombok.Getter;

@Getter
public enum CommunityPostException implements ExceptionType {

    NON_EXISTENT_CONCERN_POST(300, "존재하지 않는 질환고민 게시글입니다."),
    NON_EXISTENT_SURGERY_REVIEW_POST(301, "존재하지 않는 수술후기 게시글입니다."),
    NON_EXISTENT_DAILY_POST(302, "존재하지 않는 일상 게시글입니다."),
    NON_EXISTENT_DOCTOR_TREATMENT_REVIEW_POST(303, "존재하지 않는 의사 상담 후기 게시글입니다."),
    NON_EXISTENT_HOSPITAL_REVIEW_POST(304, "존재하지 않는 병원 후기 게시글입니다."),
    NON_EXISTENT_COMMUNITY_TYPE(305, "존재하지 않는 커뮤니티 타입입니다."),
    NOT_CONTAINS_COMMUNITY(306, "커뮤니티에 포함되지 않습니다."),
    ;

    private final int code;
    private final String message;

    CommunityPostException(final int code, final String message) {
        this.code = code;
        this.message = message;
    }
}
