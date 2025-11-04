package com.ohgiraffers.geogieoddae.restaurant.command.service.impl;

import com.ohgiraffers.geogieoddae.restaurant.command.dto.KeywordDto;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.keyword.KeywordEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.repository.keyword.KeywordRepository;
import com.ohgiraffers.geogieoddae.restaurant.command.service.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeywordServiceImpl implements KeywordService {
    private final KeywordRepository keywordRepository;

    @Override
    public void keyWordCreate(KeywordDto keywordDTO) {
        KeywordEntity keywordEntity = KeywordEntity.builder().
                keyword(keywordDTO.getKeyword()).
                keywordCategory(keywordDTO.getKeywordCategory())
                .build();

        keywordRepository.save(keywordEntity);
    }

    @Override
    public void keyWordRemove(Long keywordId) {
        keywordRepository.deleteById(keywordId);
    }
}
