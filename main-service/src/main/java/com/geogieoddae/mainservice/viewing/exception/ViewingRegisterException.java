package com.geogieoddae.mainservice.viewing.exception;

import com.geogieoddae.mainservice.exception.ErrorCode;
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
