package com.ohgiraffers.geogieoddae.admin.command.service;

public interface AdminService {
    // 관리자 로그인
    boolean login(String adminId, String adminPassword);
    // 관리자 로그아웃
    void logout();
}
