package com.ohgiraffers.geogieoddae.restaurant.command.service.impl;

import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.auth.command.repository.UserRepository;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.bookmark.BookmarkEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.bookmark.BookmarkKey;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.repository.bookmark.BookmarkRepository;
import com.ohgiraffers.geogieoddae.restaurant.command.repository.restaurant.RestaurantRepository;
import com.ohgiraffers.geogieoddae.restaurant.command.service.BookmarkService;
import com.ohgiraffers.geogieoddae.restaurant.command.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {

    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final BookmarkRepository bookmarkRepository;

    @Override
    public void registerBookmark(Long userId, Long restaurantId) {
        UserEntity user = userRepository.findById(userId).orElse(new UserEntity());
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId).orElse(new RestaurantEntity());
        BookmarkKey key = new BookmarkKey(user.getUserCode(), restaurant.getRestaurantCode());


        BookmarkEntity boo22 = new BookmarkEntity(key,null,restaurant);

        BookmarkEntity bookmark = BookmarkEntity.builder()
                .member(user)
                .id(key)
                .restaurant(restaurant)
                .build();

        bookmark.setMember(user);
        bookmarkRepository.save(bookmark);


    }

    @Override
    public void deleteBookmark(Long id) {
        bookmarkRepository.deleteById(id);

    }
}
