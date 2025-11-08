package com.geogieoddae.mainservice.restaurant.command.repository.keyword;

import com.geogieoddae.mainservice.restaurant.command.entity.keyword.KeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends JpaRepository<KeywordEntity, Long> {
}
