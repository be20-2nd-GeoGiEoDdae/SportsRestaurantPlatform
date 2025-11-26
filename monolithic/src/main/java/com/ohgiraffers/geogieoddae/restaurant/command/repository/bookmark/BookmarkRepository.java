package com.ohgiraffers.geogieoddae.restaurant.command.repository.bookmark;

import com.ohgiraffers.geogieoddae.restaurant.command.entity.bookmark.BookmarkEntity;
import java.util.List;

import com.ohgiraffers.geogieoddae.restaurant.command.entity.bookmark.BookmarkKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<BookmarkEntity, BookmarkKey> {
  List<BookmarkEntity> findByRestaurant_RestaurantCode(Long restaurantCode);
}
