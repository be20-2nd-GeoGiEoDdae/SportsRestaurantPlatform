package com.geogieoddae.mainservice.pay.command.repository;

import com.geogieoddae.mainservice.pay.command.entity.ViewingPayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewingPayRepository extends JpaRepository<ViewingPayEntity,Long> {

  ViewingPayEntity findByViewingPayOrderId(String viewingPayOrderId);
}
