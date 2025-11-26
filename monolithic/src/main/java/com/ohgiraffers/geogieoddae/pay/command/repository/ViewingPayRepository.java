package com.ohgiraffers.geogieoddae.pay.command.repository;

import com.ohgiraffers.geogieoddae.pay.command.entity.ViewingPayEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewingPayRepository extends JpaRepository<ViewingPayEntity,Long> {

  ViewingPayEntity findByViewingPayOrderId(String viewingPayOrderId);
  Optional<ViewingPayEntity> findByViewing_ViewingCodeAndMember_UserCode(Long viewingCode, Long userCode);
}
