/*  관리자 : 최종 결과를 조립하는 서비스 클래스
 * @Author : 김성현
 * @Date : 2025-10-31
 * @Version : 1.0
 * @*/

package com.ohgiraffers.geogieoddae.admin.query.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ohgiraffers.geogieoddae.admin.query.dto.UserDetailDto;
import com.ohgiraffers.geogieoddae.admin.query.dto.UserDto;
import com.ohgiraffers.geogieoddae.admin.query.mapper.UserQueryMapper;
import com.ohgiraffers.geogieoddae.global.common.dto.PageInfoDto;
import com.ohgiraffers.geogieoddae.global.common.dto.PageResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)  // 조회 기능
public class UserQueryService {

    private final UserQueryMapper userQueryMapper;

    public PageResponseDto<UserDto> selectAllUsers(int page, int size) {
        // 1. SQL의 LIMIT 에 사용할 offset 계산
        int offset = (page - 1) * size;

        // 2. 페이징 처리된 회원 목록 조회
        List<UserDto> users = userQueryMapper.selectAllUsers(offset, size);

        // 3. 전체 회원 수 조회
        int totalCount = userQueryMapper.selectAllUserCount();

        // 4. 페이지 정보 계산
        PageInfoDto pageInfo = new PageInfoDto(page, size, totalCount);

        // 5. 페이징 응답 DTO 로 감싸서 반환
        return new PageResponseDto<>(users, pageInfo);
    }

    public PageResponseDto<UserDto> selectUsersByRole(int page, int size, String userRole) {
        int offset = (page - 1) * size;
        List<UserDto> users = userQueryMapper.selectUsersByRole(offset, size, userRole);
        int totalCount = userQueryMapper.selectUserCountByRole(userRole);
        PageInfoDto pageInfo = new PageInfoDto(page, size, totalCount);
        return new PageResponseDto<>(users, pageInfo);
    }

    public PageResponseDto<UserDto> searchUsers(int page, int size, String userEmail, String userName, String userPhoneNumber) {
        int offset = (page - 1) * size;
        List<UserDto> users = userQueryMapper.searchUsers(offset, size, userEmail, userName, userPhoneNumber);
        int totalCount = userQueryMapper.countUsers(userEmail, userName, userPhoneNumber);
        PageInfoDto pageInfo = new PageInfoDto(page, size, totalCount);
        return new PageResponseDto<>(users, pageInfo);
    }

    public UserDetailDto selectUserDetail(Long userCode) {
        System.out.println(">>>> [UserQueryService] selectUserDetail 메서드 실행됨. UserCode: " + userCode);

        UserDetailDto userDetail = userQueryMapper.selectUserDetail(userCode);

        if (userDetail == null) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다. UserCode: " + userCode);
        }
        
        System.out.println(">>>> [UserQueryService] 사용자 조회 완료: " + userDetail.getUserName());
        return userDetail;
    }

}
