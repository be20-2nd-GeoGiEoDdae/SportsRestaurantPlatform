package com.ohgiraffers.geogieoddae.auth.command.controller;

import com.ohgiraffers.geogieoddae.auth.command.dto.EntrepreneurRegisterResponse;
import com.ohgiraffers.geogieoddae.auth.command.entity.entrepreneur.EntrepreneurEntity;
import com.ohgiraffers.geogieoddae.auth.command.service.EntrepreneurRegisterService;
import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/entrepreneur")
public class EntrepreneurController {
    private final EntrepreneurRegisterService entrepreneurRegisterService;

    public EntrepreneurController(EntrepreneurRegisterService entrepreneurRegisterService) {
        this.entrepreneurRegisterService = entrepreneurRegisterService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> entrepreneurRegister(@RequestBody String request) {
        entrepreneurRegisterService.registerEntrepreneur(request);
        return ResponseEntity.ok(ApiResponse.success("사업자 등록신청 완료"));
    }
}
