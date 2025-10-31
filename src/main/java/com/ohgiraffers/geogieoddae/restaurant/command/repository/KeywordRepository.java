package com.ohgiraffers.geogieoddae.restaurant.command.repository;

import com.ohgiraffers.geogieoddae.restaurant.command.entity.keyword.KeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends JpaRepository<KeywordEntity, Long> {
}
