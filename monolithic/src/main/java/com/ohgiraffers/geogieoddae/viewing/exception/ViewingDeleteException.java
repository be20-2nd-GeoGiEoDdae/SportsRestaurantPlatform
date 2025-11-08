package com.ohgiraffers.geogieoddae.viewing.exception;

import com.ohgiraffers.geogieoddae.exception.ErrorCode;
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
