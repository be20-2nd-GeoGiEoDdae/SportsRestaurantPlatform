package com.ohgiraffers.geogieoddae.viewing.exception;

import com.ohgiraffers.geogieoddae.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ViewingUpdateException extends RuntimeException {
    private final ErrorCode errorCode;
    public ViewingUpdateException(ErrorCode errorCode)
    {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
