package com.geogieoddae.userservice.auth.command.repository;

import com.geogieoddae.userservice.auth.command.entity.entrepreneur.EntrepreneurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepreneurRepository extends JpaRepository<EntrepreneurEntity, Long> {
    // 기본 CRUD 로직 구현
}
