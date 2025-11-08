package com.geogieoddae.mainservice.viewing.exception.handler;

import com.geogieoddae.mainservice.global.common.dto.ApiResponse;
import com.geogieoddae.mainservice.viewing.exception.ViewingDeleteException;
import com.geogieoddae.mainservice.viewing.exception.ViewingRegisterException;
import com.geogieoddae.mainservice.viewing.exception.ViewingUpdateException;
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
