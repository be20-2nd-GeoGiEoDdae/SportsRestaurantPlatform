package com.geogieoddae.mainservice.report.command.entity;

public enum ReportStatus {
    APPROVED,   // 승인
    PENDING,    // 보류
    REJECTED;    /*거절*/


    // 상태 전이 검증 로직
    public boolean canChangeTo(ReportStatus target) {
        // 예시: 이미 승인된 건 다시 바꾸지 못하게
        if (this == APPROVED && target != APPROVED) {
            return false;
        }
        return true;
    }

    }