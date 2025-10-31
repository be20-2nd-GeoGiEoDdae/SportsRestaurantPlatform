package com.ohgiraffers.geogieoddae.restaurant.command.dto;

import com.ohgiraffers.geogieoddae.restaurant.command.entity.keyword.KeywordCategory;
import lombok.Getter;


public class KeywordDto {

    private String keyword; // 키워드
    private KeywordCategory keywordCategory; //키워드 카테고리

    public String getKeyword() {
        return keyword;
    }

    public KeywordCategory getKeywordCategory() {
        return keywordCategory;
    }
}
