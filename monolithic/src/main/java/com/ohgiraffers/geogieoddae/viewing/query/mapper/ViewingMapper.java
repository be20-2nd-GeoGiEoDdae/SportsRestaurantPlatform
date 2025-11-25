package com.ohgiraffers.geogieoddae.viewing.query.mapper;

import com.ohgiraffers.geogieoddae.viewing.query.dto.ViewingDto;
import com.ohgiraffers.geogieoddae.viewing.query.dto.ViewingPictureDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

@Mapper
public interface ViewingMapper {

    List<ViewingDto> searchViewingsByKeyword(@Param("keyword") String keyword);

    List<ViewingDto> findAllViewings(
            @Param("lat") Double lat,
            @Param("lng") Double lng,
            @Param("limit") int limit,
            @Param("offset") int offset
    );

    int countAllViewings();


    List<ViewingDto> findViewingsByCondition(Map<String, Object> conditions);

    ViewingPictureDto findViewingDetail(@Param("viewingCode") Long viewingCode);
    List<String> findRestaurantKeywords(@Param("restaurantCode") Long restaurantCode);
    // 상세조회 시 사진 URL 조회용
    List<String> findRestaurantPictures(@Param("restaurantCode") Long restaurantCode);
}
