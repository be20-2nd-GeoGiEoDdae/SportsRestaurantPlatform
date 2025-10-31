package com.ohgiraffers.geogieoddae.restaurant.command.service;

import com.ohgiraffers.geogieoddae.restaurant.command.dto.KeywordDto;
import org.springframework.stereotype.Service;

@Service
public interface KeywordService {
    // 가게 키워드 추가 (관리자)
    void keyWordCreate(KeywordDto keywordDTO);
    // 가게 키워드 제거 (관리자)
    void keyWordRemove(Long keywordId);
}
