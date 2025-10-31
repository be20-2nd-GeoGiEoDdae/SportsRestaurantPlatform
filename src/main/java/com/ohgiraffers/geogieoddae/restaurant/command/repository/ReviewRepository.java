package com.ohgiraffers.geogieoddae.restaurant.command.repository;

import com.ohgiraffers.geogieoddae.restaurant.command.entity.review.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
}
