package com.geogieoddae.mainservice.viewing.query.service;

import com.geogieoddae.mainservice.viewing.query.dto.ViewingDto;
import com.geogieoddae.mainservice.viewing.query.dto.ViewingPictureDto;
import com.geogieoddae.mainservice.viewing.query.mapper.ViewingMapper;
import lombok.RequiredArgsConstructor;
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
    //목록 조회 (시간 순 정렬)
    public List<ViewingDto> findAllViewings() {
        return viewingMapper.findAllViewings();
    }
    //조건별 조회
    public List<ViewingDto> findViewingsByCondition(Map<String, Object> conditions) {
        return viewingMapper.findViewingsByCondition(conditions);
    }
    //상세 조회
    public ViewingPictureDto findViewingDetail(Long viewingCode) {
        ViewingPictureDto viewing = viewingMapper.findViewingDetail(viewingCode);
        if (viewing != null) {
            List<String> pictureUrls = viewingMapper.findRestaurantPictures(
                    viewing.getViewingCode()
            );
            viewing.setPictureUrls(pictureUrls);
        }
        return viewing;
    }
}
