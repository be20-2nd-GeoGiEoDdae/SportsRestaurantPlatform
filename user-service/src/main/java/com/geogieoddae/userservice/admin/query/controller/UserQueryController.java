package com.geogieoddae.userservice.admin.query.controller;

import com.geogieoddae.userservice.admin.query.dto.UserDto;
import com.geogieoddae.userservice.admin.query.service.UserQueryService;
import com.geogieoddae.userservice.global.common.dto.ApiResponse;
import com.geogieoddae.userservice.global.common.dto.PageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class UserQueryController {

    private final UserQueryService userQueryService;

    @GetMapping("/users-view")
    public ResponseEntity<ApiResponse<PageResponseDto<UserDto>>> selectAllUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        // 메서드 동작 확인
        System.out.println(">>>> [UserQueryController] selectedAllUsers 메서드 실행됨.");

        PageResponseDto<UserDto> pageResponse = userQueryService.selectAllUsers(page, size);
        //PageResponseDto<UserDto> pagedUsers = userQueryService.selectAllUsers(page, size);
        return ResponseEntity.ok(ApiResponse.success(pageResponse));
    }
}
