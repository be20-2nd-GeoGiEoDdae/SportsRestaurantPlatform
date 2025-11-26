/*  관리자 : 전체 회원 조회
 * @Author : 김성현
 * @Date : 2025-10-31
 * @Version : 1.0
 * @*/

package com.ohgiraffers.geogieoddae.admin.query.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ohgiraffers.geogieoddae.admin.query.dto.UserDetailDto;
import com.ohgiraffers.geogieoddae.admin.query.dto.UserDto;

@Mapper
public interface UserQueryMapper {
    // 페이징 처리된 회원 목록 조회
    List<UserDto> selectAllUsers(@Param("offset") int offset, @Param("size") int size);

    List<UserDto> selectUsersByRole(
        @Param("offset") int offset,
        @Param("size") int size,
        @Param("userRole") String userRole
    );

    List<UserDto> searchUsers(
        @Param("offset") int offset,
        @Param("size") int size,
        @Param("userEmail") String userEmail,
        @Param("userName") String userName,
        @Param("userPhoneNumber") String userPhoneNumber
    );

    int countUsers(
        @Param("userEmail") String userEmail,
        @Param("userName") String userName,
        @Param("userPhoneNumber") String userPhoneNumber
    );
    
    // 전체 회원 수 조회 (페이징 계산용)
    int selectAllUserCount();

    // 권한별 회원 수 조회 
    int selectUserCountByRole(@Param("userRole") String userRole);

    // 특정 회원의 상세 정보 조회
    UserDetailDto selectUserDetail(@Param("userCode") Long userCode);

}


