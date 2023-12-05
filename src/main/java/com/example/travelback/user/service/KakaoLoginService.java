//package com.example.travelback.user.service;
//
//import com.example.travelback.user.dto.KaKaoDataForm;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KakaoLoginService {
//
//    @Autowired
//    private KakaoService kakaoService;
//
//    @Autowired
//    public KakaoLoginService(KakaoService kakaoService) {
//        this.kakaoService = kakaoService;
//    }
//
//    public KaKaoDataForm performKakaoLogin(String code) {
//        String token = kakaoService.getKaKaoAccessToken(code);
//        KaKaoDataForm kaKaoData = kakaoService.createKakaoUser(token);
//
//        return kaKaoData;
//    }
//}
