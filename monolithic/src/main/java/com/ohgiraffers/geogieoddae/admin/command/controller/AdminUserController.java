package com.ohgiraffers.geogieoddae.admin.command.controller;

import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.auth.command.service.UserService;
import com.ohgiraffers.geogieoddae.global.jwt.JwtTokenProvider;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "관리자 권한 변경 API", description = "유저 권한 변경")
@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminUserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

   // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PatchMapping("/{userId}/role")
    public ResponseEntity<Map<String, String>> changeUserRole(
            @PathVariable Long userId,
            @RequestParam String newRole) {

        UserEntity updatedUser = userService.updateRole(userId, newRole);

        String newAccessToken = jwtTokenProvider.generateUserAccessToken(updatedUser.getUserEmail(), newRole);
        String newRefreshToken = jwtTokenProvider.generateUserRefreshToken(updatedUser.getUserEmail());

        updatedUser.setUserRefreshToken(newRefreshToken);
        updatedUser.setUserRefreshTokenExpiresAt(jwtTokenProvider.getRefreshTokenExpiryAsLocalDateTime());
        userService.saveUser(updatedUser);

        // ✅ 4. 프론트로 새 토큰 반환
        Map<String, String> tokens = Map.of(
                "accessToken", newAccessToken,
                "refreshToken", newRefreshToken
        );

        return ResponseEntity.ok(tokens);
    }

}



