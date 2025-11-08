package com.geogieoddae.mainservice.pay.command.entity;

import com.geogieoddae.mainservice.global.common.entity.BaseTimeEntity;
import com.geogieoddae.mainservice.viewing.command.entity.ViewingEntity;
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

    @Column(name = "viewing_pay_customer_key")
    private String viewingPayCustomerKey;

    @Column(name = "viewing_pay_order_id")
    private String viewingPayOrderId;

  @Column(name = "viewing_pay_payment_key")
  private String viewingPayPaymentKey;

  @Enumerated(EnumType.STRING)
  @Column(name= "viewing_pay_status")
  private ViewingPayStatus  viewingPayStatus;

   private Long memberCode;

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
