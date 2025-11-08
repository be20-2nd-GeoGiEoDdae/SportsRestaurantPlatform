package com.geogieoddae.mainservice.viewing.exception;

import com.geogieoddae.mainservice.exception.ErrorCode;
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
