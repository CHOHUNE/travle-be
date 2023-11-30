//package com.example.travelback.user.service;
//
//import com.example.travelback.user.dto.KaKaoDataForm;
//import lombok.RequiredArgsConstructor;
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
//    public void KakaoLoginService(KakaoService kakaoService) {
//        this.kakaoService = kakaoService;
//    }
//
//    public ResponseEntity<?> performKakaoLogin(String code) {
//        String token = kakaoService.getKaKaoAccessToken(code);
//        KaKaoDataForm kaKaoData = kakaoService.createKakaoUser(token);
//
//        // 여기서 카카오로부터 받아온 정보를 이용해 로그인 처리를 수행
//        // 예를 들어, 해당 사용자가 이미 회원 가입되어 있는지 확인하고, 회원이라면 로그인 처리를 수행
//
//        if (kaKaoData != null) {
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//
//    }
//
//
//}
