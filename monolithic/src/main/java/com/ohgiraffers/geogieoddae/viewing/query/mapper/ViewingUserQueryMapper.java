package com.ohgiraffers.geogieoddae.viewing.query.mapper;

import com.ohgiraffers.geogieoddae.viewing.query.dto.ViewingUserResponseDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ViewingUserQueryMapper {

  List<ViewingUserResponseDto> viewingUserList(Long userCode,int offset, int pageSize);

  long countPayByUserCode(Long userCode);
}
