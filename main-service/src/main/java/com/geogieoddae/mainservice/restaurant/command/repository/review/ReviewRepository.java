package com.geogieoddae.mainservice.restaurant.command.repository.review;

import com.geogieoddae.mainservice.restaurant.command.entity.review.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
}
