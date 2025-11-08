package com.geogieoddae.mainservice.restaurant.command.service.impl;

import com.geogieoddae.mainservice.restaurant.command.dto.KeywordDto;
import com.geogieoddae.mainservice.restaurant.command.entity.keyword.KeywordEntity;
import com.geogieoddae.mainservice.restaurant.command.repository.keyword.KeywordRepository;
import com.geogieoddae.mainservice.restaurant.command.service.KeywordService;
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
