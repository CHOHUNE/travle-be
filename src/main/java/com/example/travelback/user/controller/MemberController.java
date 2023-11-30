package com.example.travelback.user.controller;

import com.example.travelback.user.dto.KaKaoDataForm;
import com.example.travelback.user.dto.Member;
import com.example.travelback.user.service.KakaoLoginService;
import com.example.travelback.user.service.KakaoService;
import com.example.travelback.user.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService service;
    private final KakaoLoginService kakaoLoginService;
    private final KakaoService kakaoService;


    @Value("${Rest.api.key}")
    private String RestApiKey;

    @Value("${Redirect.uri}")
    private String redirectUri;

    // -------------------- 회원가입 로직 --------------------
    @PostMapping("signup")
    public ResponseEntity signup(@RequestBody Member member) {
        if (service.validate(member)) {
            if (service.insert(member)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.internalServerError().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    // -------------------- 로그인 로직 --------------------
    @PostMapping("login")
    public ResponseEntity login(@RequestBody Member member, WebRequest request) {
        if (service.login(member, request)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping
    public ResponseEntity<Member> view(@SessionAttribute(value = "login", required = false) String userId, Member login) {
        if (login == null) {
            // UNAUTHORIZED : 사용자가 로그인 하지 않거나, 올바르지 않은 인증정보를 제공한것
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (!service.hasAccess(userId, login)) {
            // FORBIDDEN : 인증은 성공했지만, 접근권한이 없음 (관리자, 유저) 간 관계
            // 관리자는 모든 권한이 있지만 유저는 그렇지 않음
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Member member = service.getMember(userId);
        return ResponseEntity.ok(member);
    }

    // -------------------- 카카오 로그인 api key, redirecturi --------------------
    @GetMapping("kakaoKey")
    public Map<String, String> kakaoKey() {
        return Map.of("key", RestApiKey, "redirect", redirectUri);
    }
    // -------------------- 카카오 로그인 로직 --------------------
    @GetMapping("/oauth2/kakao")
    public String kakaoLogin() {
        // 카카오 로그인 URL 생성
        String kakaoUrl = "https://kauth.kakao.com/oauth/authorize?client_id=" + RestApiKey +
                          "&redirect_uri=" + redirectUri +
                          "&response_type=code";
        System.out.println("Kakao Login URL: " + kakaoUrl);
        return "redirect:" + kakaoUrl;
    }

    @PostMapping("kakaoLogin")
    public ResponseEntity<KaKaoDataForm> kakaoLogin(@RequestParam String code, HttpSession session) {
//        String token = kakaoService.getKaKaoAccessToken(code);
//        KaKaoDataForm res = kakaoService.createKakaoUser(token);
        KaKaoDataForm kakaoData = kakaoLoginService.performKakaoLogin(code);

        if (kakaoData != null) {
            session.setAttribute("kakadoUserInfo", kakaoData);
            return ResponseEntity.ok(kakaoData);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    // -------------------- id 중복체크 로직 --------------------
    @GetMapping(value = "check", params = "userId")
    public ResponseEntity checkId(String userId) {
        if (service.getUserId(userId) == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }


}
