package com.ohgiraffers.geogieoddae.admin.query.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ohgiraffers.geogieoddae.admin.query.dto.EntrepreneurUpdateRequest;
import com.ohgiraffers.geogieoddae.admin.query.dto.UserDetailDto;
import com.ohgiraffers.geogieoddae.admin.query.service.AdminEntrepreneurService;
import com.ohgiraffers.geogieoddae.admin.query.service.UserQueryService;
import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminEntrepreneurController {

  private final AdminEntrepreneurService service;
  private final UserQueryService userQueryService;

  @GetMapping("/users/entrepreneurs/{userCode}")
  public ApiResponse<UserDetailDto> getCombined(@PathVariable Long userCode) {
    UserDetailDto entrepreneurDto = userQueryService.selectUserDetail(userCode);
    System.out.println(">>>> [UserQueryController] selectEntrepreneurDetail 메서드 실행됨. UserCode: " + userCode);
    return ApiResponse.success(entrepreneurDto);
  }

  @PutMapping("/users/entrepreneurs/{entrepreneurCode}")
  public ApiResponse<Void> updateEntrepreneur(@PathVariable Long entrepreneurCode,
                                              @Valid @RequestBody EntrepreneurUpdateRequest req) {
    service.updateEntrepreneur(entrepreneurCode, req);
    return ApiResponse.success(null);
  }
}
