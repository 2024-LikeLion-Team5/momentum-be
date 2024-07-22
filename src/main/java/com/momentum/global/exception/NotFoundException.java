package com.momentum.global.exception;

public class NotFoundException extends BaseException {

    public NotFoundException(final ExceptionType exceptionType) {
        super(exceptionType);
    }
}
