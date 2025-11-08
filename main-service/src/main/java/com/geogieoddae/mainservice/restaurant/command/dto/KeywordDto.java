package com.geogieoddae.mainservice.restaurant.command.dto;

import com.geogieoddae.mainservice.restaurant.command.entity.keyword.KeywordCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class KeywordDto {
    @NotBlank
    private String keyword; // 키워드
    @NotBlank
    private KeywordCategory keywordCategory; //키워드 카테고리


}
