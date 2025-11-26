package com.ohgiraffers.geogieoddae.pay.query.mapper;

import com.ohgiraffers.geogieoddae.pay.query.dto.BillingQueryDto;
import com.ohgiraffers.geogieoddae.pay.query.dto.BillingQueryFindUserResponse;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BillingQueryMapper {

  List<BillingQueryDto> subscribeFindAll() ;

  List<BillingQueryFindUserResponse> subscribeFindByUserCode(Long userCode,int offset,int pageSize);

  long countByUserCode(Long userCode);
}
