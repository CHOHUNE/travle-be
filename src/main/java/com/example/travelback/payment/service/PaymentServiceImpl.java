//package com.example.travelback.payment.service;
//
//import com.example.travelback.payment.config.TossPaymentConfig;
//import com.example.travelback.payment.entity.Payment;
//import com.example.travelback.payment.repository.JpaPaymentRepository;
//import com.example.travelback.user.service.MemberService;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.lang.reflect.Member;
//
//@Service
//@Transactional
//public class PaymentServiceImpl implements PaymentService  {
//    private final JpaPaymentRepository paymentRepository;
//    private final MemberService memberService;
//    private final TossPaymentConfig tossPaymentConfig;
//    public Payment requestTossPayment(Payment payment, String userEmail) {
//        Member member = memberService.findMember(userEmail);
//        if (payment.getAmount() < 1000) {
//            throw new CustomLogicException(ExceptionCode.INVALID_PAYMENT_AMOUNT);
//        }
//        payment.setCustomer(member);
//        return paymentRepository.save(payment);
//    }
//}
