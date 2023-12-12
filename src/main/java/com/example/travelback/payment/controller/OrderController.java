//package com.example.travelback.payment.controller;
//
//import com.example.travelback.payment.order.Orders;
//import com.example.travelback.payment.service.OrderWaitService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/order")
//public class OrderController {
//
//    private final OrderWaitService orderWaitService;
//
//    @PostMapping("/orderWait")
//    public void orderWait(@RequestBody Orders orders) {
//        orderWaitService.insert(orders);
//    }
//}
