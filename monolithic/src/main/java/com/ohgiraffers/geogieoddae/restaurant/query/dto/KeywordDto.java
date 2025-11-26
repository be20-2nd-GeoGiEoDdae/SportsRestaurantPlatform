package com.ohgiraffers.geogieoddae.restaurant.query.dto;

import com.ohgiraffers.geogieoddae.restaurant.command.entity.keyword.KeywordCategory;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KeywordDto {

    private Long keywordCode;
    private String keywordName;
    private KeywordCategory keywordCategory;
}
