package com.ohgiraffers.geogieoddae.pay.query.service;

import com.ohgiraffers.geogieoddae.pay.query.dto.PaySearchResponse;
import com.ohgiraffers.geogieoddae.pay.query.mapper.PayMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
