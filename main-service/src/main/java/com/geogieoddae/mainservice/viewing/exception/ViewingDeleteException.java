package com.geogieoddae.mainservice.viewing.exception;

import com.geogieoddae.mainservice.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ViewingDeleteException extends RuntimeException {

    private final ErrorCode errorCode;
    public ViewingDeleteException(ErrorCode errorCode)
    {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
