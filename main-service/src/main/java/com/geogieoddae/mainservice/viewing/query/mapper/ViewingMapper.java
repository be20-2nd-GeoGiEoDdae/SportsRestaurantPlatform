package com.geogieoddae.mainservice.viewing.query.mapper;

import com.geogieoddae.mainservice.viewing.query.dto.ViewingDto;
import com.geogieoddae.mainservice.viewing.query.dto.ViewingPictureDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ViewingMapper {

    List<ViewingDto> searchViewingsByKeyword(@Param("keyword") String keyword);

    List<ViewingDto> findAllViewings();

    List<ViewingDto> findViewingsByCondition(Map<String, Object> conditions);

    ViewingPictureDto findViewingDetail(@Param("viewingCode") Long viewingCode);

    // 상세조회 시 사진 URL 조회용
    List<String> findRestaurantPictures(@Param("restaurantCode") Long restaurantCode);
}
