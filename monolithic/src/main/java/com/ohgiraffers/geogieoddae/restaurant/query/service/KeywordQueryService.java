package com.ohgiraffers.geogieoddae.restaurant.query.service;

import com.ohgiraffers.geogieoddae.restaurant.command.entity.keyword.KeywordEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.repository.keyword.KeywordRepository;
import com.ohgiraffers.geogieoddae.restaurant.query.dto.KeywordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordQueryService {

    private final KeywordRepository keywordRepository;

    public List<KeywordDto> getKeywordList() {

        return keywordRepository.findAll()
                .stream()
                .map(k ->
                        new KeywordDto(
                                k.getKeywordCode(),
                                k.getKeyword(),
                                k.getKeywordCategory()
                        )
                )
                .toList();
    }
}
