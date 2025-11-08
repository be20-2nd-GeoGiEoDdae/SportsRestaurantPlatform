/* 로그인/회원가입 성공 시, 우리 서비스의 jwt 토큰을 담아 반환할 DTO(임시그릇)
 * @Author : 김성현
 * @Date : 2025-11-01
 * @Version : 1.0
 */

package com.geogieoddae.userservice.auth.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
}