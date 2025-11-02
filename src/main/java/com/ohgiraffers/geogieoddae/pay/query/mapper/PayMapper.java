package com.ohgiraffers.geogieoddae.pay.query.mapper;

import com.ohgiraffers.geogieoddae.pay.query.dto.PaySearchResponse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayMapper {
  PaySearchResponse selectPayByPayCode(Long payCode);
}
