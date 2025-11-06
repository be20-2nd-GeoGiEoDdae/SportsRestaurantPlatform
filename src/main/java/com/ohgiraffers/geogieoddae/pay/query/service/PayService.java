package com.ohgiraffers.geogieoddae.pay.query.service;

import com.ohgiraffers.geogieoddae.pay.query.dto.PaySearchResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;


import com.ohgiraffers.geogieoddae.pay.query.mapper.PayMapper;
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
  public List<PaySearchResponse> selectPayByUserCode(Long userCode){
    List<PaySearchResponse> paySearchResponse
        =payMapper.selectPayByUserCode(userCode);
    return  paySearchResponse;
  }

}
