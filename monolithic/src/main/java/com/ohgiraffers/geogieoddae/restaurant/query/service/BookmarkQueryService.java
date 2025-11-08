package com.ohgiraffers.geogieoddae.restaurant.query.service;

import com.ohgiraffers.geogieoddae.restaurant.query.mapper.BookmarkMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookmarkQueryService {
    private final BookmarkMapper bookmarkMapper;
    public List<Map<String, Object>> getBookmarksByUser(Long userCode) {
        return bookmarkMapper.findBookmarksByUserId(userCode);
    }
}
