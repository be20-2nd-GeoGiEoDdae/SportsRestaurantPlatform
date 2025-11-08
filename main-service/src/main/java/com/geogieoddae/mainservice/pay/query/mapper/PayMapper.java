package com.geogieoddae.mainservice.pay.query.mapper;

import com.geogieoddae.mainservice.pay.query.dto.PaySearchResponse;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayMapper {
  PaySearchResponse selectPayByPayCode(Long payCode);

  List<PaySearchResponse> selectPayByViewingCode(Long viewingCode);

  List<PaySearchResponse> selectPayAll();

  List<PaySearchResponse> selectPayByUserCode(Long userCode);
}
