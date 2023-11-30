//package com.example.travelback.user.controller;
//
//import com.example.travelback.user.service.KakaoService;
//import jakarta.servlet.http.HttpSession;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//@RequiredArgsConstructor
//public class AuthController {
//    private final KakaoService kakaoService;
//
//    @Value("${Redirect.uri}")
//    private String redirecturi;
//
//    @GetMapping("/oauth/kakao/callback")
//    public String kakaoLoginCallback(@RequestParam String code) {
//
//        return "redirect:/";
//    }
//}
