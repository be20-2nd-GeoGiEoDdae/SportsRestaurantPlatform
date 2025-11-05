package com.ohgiraffers.geogieoddae.auth.command.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ohgiraffers.geogieoddae.auth.command.dto.EntrepreneurRegisterRequestDto;
import com.ohgiraffers.geogieoddae.auth.command.service.EntrepreneurService;
import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;

@RestController
@RequestMapping("/api/auth/entrepreneur")
public class EntrepreneurController {
    private final EntrepreneurService entrepreneurService;

    public EntrepreneurController(EntrepreneurService entrepreneurRegisterService) {
        this.entrepreneurService = entrepreneurRegisterService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> entrepreneurRegister(@RequestBody EntrepreneurRegisterRequestDto request) {
        entrepreneurService.register(request);
        return ResponseEntity.ok(ApiResponse.success("사업자 등록신청 완료"));
    }

}
