package com.geogieoddae.userservice.admin.query.service;/*  관리자 : 최종 결과를 조립하는 서비스 클래스
 * @Author : 김성현
 * @Date : 2025-10-31
 * @Version : 1.0
 * @*/


import com.geogieoddae.userservice.admin.query.dto.UserDto;
import com.geogieoddae.userservice.admin.query.mapper.UserQueryMapper;
import com.geogieoddae.userservice.global.common.dto.PageInfoDto;
import com.geogieoddae.userservice.global.common.dto.PageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
