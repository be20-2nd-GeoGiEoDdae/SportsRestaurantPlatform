
package com.ohgiraffers.geogieoddae.pay.command.repository;

import com.ohgiraffers.geogieoddae.pay.command.entity.EntrepreneurSubscribePaymentEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepreneurSubscribePaymentRepository extends JpaRepository<EntrepreneurSubscribePaymentEntity, Long> {

  EntrepreneurSubscribePaymentEntity findByEntrepreneurSubscribeCustomerKey(String customerKey);


  EntrepreneurSubscribePaymentEntity findByEntrepreneurCode(Long entrepreneurCode);

  EntrepreneurSubscribePaymentEntity findByEntrepreneurSubscribeBillingkey(String billingKey);

  List<EntrepreneurSubscribePaymentEntity> findByEntrepreneurSubscribeEndAtBetween(LocalDateTime start, LocalDateTime end);
}

