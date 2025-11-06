package com.ohgiraffers.geogieoddae.viewing.command.repository;

import com.ohgiraffers.geogieoddae.viewing.command.entity.ViewingUserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewingUserRepository extends JpaRepository<ViewingUserEntity,Long> {
  List<ViewingUserEntity> findByViewing_ViewingCode(Long viewingCode);
}
