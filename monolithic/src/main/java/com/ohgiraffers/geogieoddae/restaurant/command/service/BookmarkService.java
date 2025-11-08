package com.ohgiraffers.geogieoddae.restaurant.command.service;

public interface BookmarkService {

    //즐겨찾기 등록
    void registerBookmark(Long userId, Long restaurantId);
    //즐겨찾기 삭제
    void deleteBookmark(Long id);

}
