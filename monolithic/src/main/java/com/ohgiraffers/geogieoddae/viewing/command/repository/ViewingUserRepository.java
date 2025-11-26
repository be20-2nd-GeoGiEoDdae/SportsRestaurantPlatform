package com.ohgiraffers.geogieoddae.viewing.command.repository;

import com.ohgiraffers.geogieoddae.viewing.command.entity.ViewingUserEntity;
import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewingUserRepository extends JpaRepository<ViewingUserEntity,Long> {
  List<ViewingUserEntity> findByViewing_ViewingCode(Long viewingCode);
    boolean existsByViewing_ViewingCode(Long viewingCode);

  ViewingUserEntity findByViewing_ViewingCodeAndMember_UserCode(Long viewingCode, Long userCode);
}
