//package com.example.travelback.payment.service;
//
//import com.example.travelback.payment.Payment.Payment;
//import com.example.travelback.payment.dto.PaymentDto;
//import com.example.travelback.payment.mapper.PaymentMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class EntityService {
//
//    private final PaymentMapper paymentMapper;
//
//    public Payment toEntity(PaymentDto paymentDto) {
//        paymentMapper.insertByDTO(paymentDto);
//        return paymentMapper.selectByDTO(paymentDto);
//    }
//}
