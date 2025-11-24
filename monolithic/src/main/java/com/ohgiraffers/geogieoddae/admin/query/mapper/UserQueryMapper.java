/*  관리자 : 전체 회원 조회
 * @Author : 김성현
 * @Date : 2025-10-31
 * @Version : 1.0
 * @*/

package com.ohgiraffers.geogieoddae.admin.query.mapper;

import com.ohgiraffers.geogieoddae.admin.query.dto.UserDto;
import com.ohgiraffers.geogieoddae.admin.query.dto.UserDetailDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserQueryMapper {
    // 페이징 처리된 회원 목록 조회
    List<UserDto> selectAllUsers(@Param("offset") int offset, @Param("limit") int limit);

    // 전체 회원 수 조회 (페이징 계산용)
    int selectAllUserCount();

    // 특정 회원의 상세 정보 조회
    UserDetailDto selectUserDetail(@Param("userCode") Long userCode);
}


