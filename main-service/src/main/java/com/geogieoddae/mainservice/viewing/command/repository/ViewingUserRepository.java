package com.geogieoddae.mainservice.viewing.command.repository;

import com.geogieoddae.mainservice.viewing.command.entity.ViewingUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewingUserRepository extends JpaRepository<ViewingUserEntity,Long> {
  List<ViewingUserEntity> findByViewing_ViewingCode(Long viewingCode);
    boolean existsByViewing_ViewingCode(Long viewingCode);
}
