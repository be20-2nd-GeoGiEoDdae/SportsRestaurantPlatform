package com.geogieoddae.mainservice.restaurant.command.repository.review;

import com.geogieoddae.mainservice.restaurant.command.entity.review.ReviewEntity;
import com.geogieoddae.mainservice.restaurant.command.entity.review.ReviewPictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewPictureRepository extends JpaRepository<ReviewPictureEntity, Long> {
    List<ReviewPictureEntity> findByReview(ReviewEntity review);
}
