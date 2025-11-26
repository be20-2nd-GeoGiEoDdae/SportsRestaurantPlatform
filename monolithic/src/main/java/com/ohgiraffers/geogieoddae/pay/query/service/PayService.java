package com.ohgiraffers.geogieoddae.pay.query.service;

import com.ohgiraffers.geogieoddae.pay.query.dto.PaySearchResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;


import com.ohgiraffers.geogieoddae.pay.query.mapper.PayMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PayService {
  private final PayMapper payMapper;
  @Transactional()
  public PaySearchResponse selectPay(Long payCode){
    PaySearchResponse paySearchResponse
        =payMapper.selectPayByPayCode(payCode);
      return paySearchResponse;
  }
  @Transactional()
  public List<PaySearchResponse> selectPayByViewingCode(Long viewingCode){
    List<PaySearchResponse> paySearchResponse
        =payMapper.selectPayByViewingCode(viewingCode);
    return paySearchResponse;
  }

  public List<PaySearchResponse> selectPayAll() {
    List<PaySearchResponse> paySearchResponse
        =payMapper.selectPayAll();
    return paySearchResponse;
  }
  public Page<PaySearchResponse> selectPayByUserCode(Long userCode, Pageable pageable){
    int  pageNumber= pageable.getPageNumber();
    int  pageSize= 5;
    int offset = pageNumber*pageSize;
    List<PaySearchResponse> paySearchResponse
        =payMapper.selectPayByUserCode(userCode,offset,pageSize);
    long total= payMapper.countPayByUserCode(userCode);
    return  new PageImpl<>(
        paySearchResponse, PageRequest.of(pageNumber, pageSize),total);
  }

}
