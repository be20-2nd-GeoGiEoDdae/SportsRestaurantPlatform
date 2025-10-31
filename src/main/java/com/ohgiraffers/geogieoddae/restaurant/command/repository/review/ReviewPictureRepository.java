package com.ohgiraffers.geogieoddae.restaurant.command.repository.review;

import com.ohgiraffers.geogieoddae.restaurant.command.entity.review.ReviewPictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewPictureRepository extends JpaRepository<ReviewPictureEntity, Long> {
}
