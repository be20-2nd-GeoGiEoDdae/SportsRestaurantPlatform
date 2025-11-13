package com.ohgiraffers.geogieoddae.pay.command.entity;

import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.global.common.entity.BaseTimeEntity;
import com.ohgiraffers.geogieoddae.viewing.command.entity.ViewingEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "viewing_pay")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewingPayEntity extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "viewing_pay_code")
    private Long viewingPayCode;

    @Column(name = "viewing_pay_price", nullable = false)
    private Long viewingPayPrice;

    @Column(name = "viewing_pay_refund_date")//true->
    private LocalDateTime viewingPayRefundDate;

/*    @Column(name = "viewing_pay_customer_key")
    private String viewingPayCustomerKey;*/

    @Column(name = "viewing_pay_order_id")
    private String viewingPayOrderId;

  @Column(name = "viewing_pay_payment_key")
  private String viewingPayPaymentKey;

  @Enumerated(EnumType.STRING)
  @Column(name= "viewing_pay_status")
  private ViewingPayStatus  viewingPayStatus;




    @ManyToOne
    @JoinColumn(name = "user_code", nullable = false)
    private UserEntity member;

    @ManyToOne
    @JoinColumn(name = "viewing_code", nullable = false)
    private ViewingEntity viewing;

    public void addOrderId(String orderId) {
      this.viewingPayOrderId = orderId;
    }
    public void addPaymentKey(String paymentKey) {
      this.viewingPayPaymentKey = paymentKey;
    }
}
