package com.ohgiraffers.geogieoddae.viewing.query.service;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.pay.query.dto.PaySearchResponse;
import com.ohgiraffers.geogieoddae.viewing.query.dto.ViewingUserResponseDto;
import com.ohgiraffers.geogieoddae.viewing.query.mapper.ViewingUserQueryMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViewingUserQueryService {

  private final ViewingUserQueryMapper viewingUserMapper;

  public Page<ViewingUserResponseDto> viewingUserList(Long userCode, Pageable pageable) {
    int pageNumber = pageable.getPageNumber();
    int pageSize = 5;
    int offset = pageNumber * pageSize;
    List<ViewingUserResponseDto> viewingUserResponseDtoList
        = viewingUserMapper.viewingUserList(userCode, offset, pageSize);
    long total = viewingUserMapper.countPayByUserCode(userCode);
    return new PageImpl<>(
        viewingUserResponseDtoList, PageRequest.of(pageNumber, pageSize), total);
  }
}

