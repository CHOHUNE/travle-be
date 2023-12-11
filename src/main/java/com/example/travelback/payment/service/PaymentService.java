//package com.example.travelback.payment.service;
//
//import com.example.travelback.payment.entity.Payment;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Slice;
//
//public class PaymentService {
//    Payment requestTossPayment(Payment payment, String userEmail);
//    PaymentSuccessDto tossPaymentSuccess(String paymentKey, String orderId, Long amount);
//    PaymentSuccessDto requestPaymentAccept(String paymentKey, String orderId, Long amount);
//    Slice<Payment> findAllChargingHistories(String username, Pageable pageable);
//    Payment verifyPayment(String orderId, Long amount);
//}
