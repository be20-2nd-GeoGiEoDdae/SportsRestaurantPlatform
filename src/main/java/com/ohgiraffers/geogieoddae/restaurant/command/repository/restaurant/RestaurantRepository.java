package com.ohgiraffers.geogieoddae.restaurant.command.repository.restaurant;

import com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    @Query(value = """
        SELECT 
            r.restaurant_code,
            r.restaurant_name,
            r.restaurant_location,
            r.latitude,
            r.longitude,
            (6371 * acos(
                cos(radians(:userLat))
              * cos(radians(r.latitude))
              * cos(radians(r.longitude) - radians(:userLon))
              + sin(radians(:userLat)) * sin(radians(r.latitude))
            )) AS distance
        FROM restaurant r
        ORDER BY distance ASC
        """, nativeQuery = true)
    List<Object[]> findAllOrderByDistance(@Param("userLat") double userLat,
                                          @Param("userLon") double userLon);
}


