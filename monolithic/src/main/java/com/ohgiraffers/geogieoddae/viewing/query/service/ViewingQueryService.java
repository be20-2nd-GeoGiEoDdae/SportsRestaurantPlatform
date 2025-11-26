package com.ohgiraffers.geogieoddae.viewing.query.service;

import com.ohgiraffers.geogieoddae.viewing.query.dto.ViewingDto;
import com.ohgiraffers.geogieoddae.viewing.query.dto.ViewingPictureDto;
import com.ohgiraffers.geogieoddae.viewing.query.mapper.ViewingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ViewingQueryService {

    private final ViewingMapper viewingMapper;
    //검색
    public List<ViewingDto> searchViewings(String keyword) {
        return viewingMapper.searchViewingsByKeyword(keyword);
    }

    public Page<ViewingDto> findAllViewings(
            Double lat,
            Double lng,
            int page,
            int size,
            String category,
            List<String> keywords,
            String sort
    ) {

        int offset = page * size;

        List<ViewingDto> list = viewingMapper.findAllViewingsWithFilter(
                lat, lng, category, keywords, sort, size, offset
        );

        int total = viewingMapper.countAllViewingsWithFilter(category, keywords);

        return new PageImpl<>(list, PageRequest.of(page, size), total);
    }


    //조건별 조회
    public List<ViewingDto> findViewingsByCondition(Map<String, Object> conditions) {
        return viewingMapper.findViewingsByCondition(conditions);
    }

    //상세 조회
    public ViewingPictureDto findViewingDetail(Long viewingCode) {
        ViewingPictureDto dto = viewingMapper.findViewingDetail(viewingCode);
        if (dto == null) return null;

        Long restaurantCode = dto.getRestaurantCode();

        dto.setPictureUrls(
                String.join(",", viewingMapper.findRestaurantPictures(restaurantCode))
        );

        return dto;
    }
}
