/* 회원 정보 DTO - 조회된 정보를 담아 클라이언트에 전달할 UserDTO 클래스 : 불필요한 정보 숨길수 있음
 * @Author : 김성현
 * @Date : 2025-10-31
 * @Version : 1.0
 * @*/

package com.ohgiraffers.geogieoddae.admin.query.dto;

import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDto {
    private Long userCode;
    private String userName;
    private String userEmail;
    private String userPhoneNumber;
    private String userAddress;
    private UserRole user;
    private LocalDateTime createdAt;
}