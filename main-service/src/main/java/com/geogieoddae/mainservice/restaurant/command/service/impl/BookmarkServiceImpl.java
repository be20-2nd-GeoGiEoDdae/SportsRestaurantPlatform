package com.geogieoddae.mainservice.restaurant.command.service.impl;

import com.geogieoddae.mainservice.client.UserFeignClient;
import com.geogieoddae.mainservice.client.UserInfoResponse;
import com.geogieoddae.mainservice.restaurant.command.entity.bookmark.BookmarkEntity;
import com.geogieoddae.mainservice.restaurant.command.entity.bookmark.BookmarkKey;
import com.geogieoddae.mainservice.restaurant.command.entity.restaurant.RestaurantEntity;
import com.geogieoddae.mainservice.restaurant.command.repository.bookmark.BookmarkRepository;
import com.geogieoddae.mainservice.restaurant.command.repository.restaurant.RestaurantRepository;
import com.geogieoddae.mainservice.restaurant.command.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {

    private final RestaurantRepository restaurantRepository;
    private final BookmarkRepository bookmarkRepository;
    private final UserFeignClient userFeignClient;

    @Override
    public void registerBookmark(Long userId, Long restaurantId) {
        UserInfoResponse user = userFeignClient.getUserById(userId);
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId).orElse(new RestaurantEntity());
        BookmarkKey key = new BookmarkKey(user.getUserCode(), restaurant.getRestaurantCode());


        BookmarkEntity boo22 = new BookmarkEntity(key,null,restaurant);

        BookmarkEntity bookmark = BookmarkEntity.builder()
                .memberCode(user.getUserCode())
                .id(key)
                .restaurant(restaurant)
                .build();

        bookmarkRepository.save(bookmark);


    }

    @Override
    public void deleteBookmark(Long id) {
        bookmarkRepository.deleteById(id);

    }
}
