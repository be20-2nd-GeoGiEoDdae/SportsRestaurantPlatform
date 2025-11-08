package com.ohgiraffers.geogieoddae.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum ErrorCode {
    //회원
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    DUPLICATE_USER(HttpStatus.CONFLICT, "이미 존재하는 사용자입니다."),
    //관람
    FAILED_VIEWING_REGISTER(HttpStatus.BAD_REQUEST,"입력이 유효하지 않습니다."),
    FAILED_VIEWING_UPDATE(HttpStatus.CONFLICT,"신청한 이용자가 있으면 수정할 수 없습니다."),
    FAILED_VIEWING_DELETE(HttpStatus.CONFLICT, "신청한 이용자가 있으면 수정할 수 없습니다.");
    private final HttpStatus status;
    private final String message;





}
