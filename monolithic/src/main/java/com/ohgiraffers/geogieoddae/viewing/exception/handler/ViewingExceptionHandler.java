package com.ohgiraffers.geogieoddae.viewing.exception.handler;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.viewing.exception.ViewingDeleteException;
import com.ohgiraffers.geogieoddae.viewing.exception.ViewingRegisterException;
import com.ohgiraffers.geogieoddae.viewing.exception.ViewingUpdateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ViewingExceptionHandler {


    @ExceptionHandler(ViewingRegisterException.class)
    public ResponseEntity<ApiResponse<?>> failedRegister(ViewingRegisterException e) {
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(ApiResponse.failure(e.getErrorCode().name(), e.getMessage()));
    }

    @ExceptionHandler(ViewingUpdateException.class)
    public ResponseEntity<ApiResponse<?>> failedUpdate(ViewingUpdateException e) {
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(ApiResponse.failure(e.getErrorCode().name(), e.getMessage()));
    }

    @ExceptionHandler(ViewingDeleteException.class)
    public ResponseEntity<ApiResponse<?>> failedDelete(ViewingDeleteException e) {
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(ApiResponse.failure(e.getErrorCode().name(), e.getMessage()));
    }
}
