package com.momentum.exception;

import com.momentum.global.exception.ExceptionType;
import lombok.Getter;

@Getter
public enum CommunityPostException implements ExceptionType {

    NON_EXISTENT_CONCERN_POST(300, "존재하지 않는 질환고민 게시글입니다."),
    ;

    private final int code;
    private final String message;

    CommunityPostException(final int code, final String message) {
        this.code = code;
        this.message = message;
    }
}
