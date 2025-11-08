package com.geogieoddae.mainservice.restaurant.command.repository.bookmark;

import com.geogieoddae.mainservice.restaurant.command.entity.bookmark.BookmarkEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<BookmarkEntity,Long> {
  List<BookmarkEntity> findByRestaurant_RestaurantCode(Long restaurantCode);
}
