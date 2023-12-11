//package com.example.travelback.payment.entity;
//
//import com.example.travelback.payment.dto.PayType;
//import com.example.travelback.payment.dto.PaymentResDto;
//import com.example.travelback.user.dto.Member;
//import jakarta.persistence.*;
//import lombok.*;
//import org.springframework.data.domain.Auditable;
//
//@Entity
//@Getter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Setter
//@Table(indexes = {
//        @Index(name = "idx_payment_member", columnList = "customer"),
//        @Index(name = "idx_payment_paymentKey", columnList = "paymentKey"),
//})
//public class Payment extends Auditable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "payment_id", nullable = false, unique = true)
//    private Long paymentId;
//    @Column(nullable = false, name = "pay_type")
//    @Enumerated(EnumType.STRING)
//    private PayType payType;
//    @Column(nullable = false, name = "pay_amount")
//    private Long amount;
//    @Column(nullable = false, name = "pay_name")
//    private String orderName;
//    @Column(nullable = false, name = "order_id")
//    private String orderId;
//
//    private boolean paySuccessYN;
//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "customer")
//    private Member customer;
//    @Column
//    private String paymentKey;
//    @Column
//    private String failReason;
//
//    @Column
//    private boolean cancelYN;
//    @Column
//    private String cancelReason;
//
//    public PaymentResDto toPaymentResDto() {
//        return PaymentResDto.builder()
//                .payType(payType.getDescription())
//                .amount(amount)
//                .orderName(orderName)
//                .orderId(orderId)
//                .customerEmail(customer.getUserEmail())
//                .customerName(customer.getUserName())
//                .createdAt(String.valueOf(getCustomer().getInserted()))
//                .cancelYN(cancelYN)
//                .failReason(failReason)
//                .build();
//    }
//
//
//}