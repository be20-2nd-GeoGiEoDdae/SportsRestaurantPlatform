package com.ohgiraffers.geogieoddae.viewing.exception;

import com.ohgiraffers.geogieoddae.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ViewingRegisterException extends RuntimeException {

    private final ErrorCode errorCode;
    public ViewingRegisterException(ErrorCode errorCode)
    {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
