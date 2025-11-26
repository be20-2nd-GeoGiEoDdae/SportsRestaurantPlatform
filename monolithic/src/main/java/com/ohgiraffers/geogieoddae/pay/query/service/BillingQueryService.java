package com.ohgiraffers.geogieoddae.pay.query.service;

import com.ohgiraffers.geogieoddae.pay.query.dto.BillingQueryDto;
import com.ohgiraffers.geogieoddae.pay.query.dto.BillingQueryFindUserResponse;
import com.ohgiraffers.geogieoddae.pay.query.mapper.BillingQueryMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillingQueryService {
  private final BillingQueryMapper billingQueryMapper;
  public List<BillingQueryDto> findAll(){
    return billingQueryMapper.subscribeFindAll();
  }

  public Page<BillingQueryFindUserResponse> findByUserCode(Long userCode, Pageable pageable) {
    int pageNumber = pageable.getPageNumber();     // 0,1,2,...
    int pageSize   = 5;      // 프론트에서 준 size (예: 10)

    int offset = pageNumber * pageSize;

    // 1) 현재 페이지 데이터 조회
    List<BillingQueryFindUserResponse> contents =
        billingQueryMapper.subscribeFindByUserCode(userCode, offset, pageSize);

    // 2) 전체 개수 조회 (페이징용 total)
    long total = billingQueryMapper.countByUserCode(userCode);

    // 3) PageImpl로 감싸서 반환
    return new PageImpl<>(
        contents,
        PageRequest.of(pageNumber, pageSize),
        total
    );
  }
}
