package com.ohgiraffers.geogieoddae.pay.command.entity;

import com.ohgiraffers.geogieoddae.auth.command.entity.entrepreneur.EntrepreneurEntity;
import com.ohgiraffers.geogieoddae.global.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "entrepreneur_subscribe_payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntrepreneurSubscribePaymentEntity extends BaseTimeEntity {

  @Id// @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "entrepreneur_code")
  private Long entrepreneurCode;

  @Column(name = "entrepreneur_subscribe_end_at")
  private LocalDateTime entrepreneurSubscribeEndAt;

  @Column(name = "entrepreneur_subscribe_payment")
  private Integer entrepreneurSubscribePayment;

  @Column(name = "entrepreneur_subscribe_billingkey")
  private String entrepreneurSubscribeBillingkey;

  @Column(name = "entrepreneur_subscribe_customerKey")
  private String entrepreneurSubscribeCustomerKey;

  @Column(name = "entrepreneur_subscribe_orderid")
  private String entrepreneurSubscribeOrderId;


  @ManyToOne
  @JoinColumn(name = "entrepreneur_code", insertable = false, updatable = false)
  private EntrepreneurEntity entrepreneur;

  public void updateEntrepreneurSubscribePayment(
      //Long entrepreneurCode,
     // LocalDateTime entrepreneurSubscribeEndAt
       Integer entrepreneurSubscribePayment
      , String entrepreneurSubscribeBillingkey
   //   , String entrepreneurSubscribeCustomerKey
    //  , String entrepreneurSubscribeOrderId
      ) {
 //   this.entrepreneurCode=entrepreneurCode;
  //  this.entrepreneurSubscribeEndAt=entrepreneurSubscribeEndAt;
    this.entrepreneurSubscribePayment=entrepreneurSubscribePayment;
    this.entrepreneurSubscribeBillingkey=entrepreneurSubscribeBillingkey;
  //  this.entrepreneurSubscribeCustomerKey=entrepreneurSubscribeCustomerKey;
   // this.entrepreneurSubscribeOrderId=entrepreneurSubscribeOrderId;

  }
}
