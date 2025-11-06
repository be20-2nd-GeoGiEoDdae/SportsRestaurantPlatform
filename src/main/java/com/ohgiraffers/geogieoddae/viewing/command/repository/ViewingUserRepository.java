package com.ohgiraffers.geogieoddae.viewing.command.repository;

import com.ohgiraffers.geogieoddae.viewing.command.entity.ViewingUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewingUserRepository extends JpaRepository<ViewingUserEntity,Long> {
    boolean existsByViewing_ViewingCode(Long viewingCode);
}
