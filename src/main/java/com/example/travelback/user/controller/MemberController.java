package com.example.travelback.user.controller;

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
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member/")
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
    @GetMapping("login")
    public Member login(@SessionAttribute(value = "login", required = false) Member login) {
        return login;
    }

    // -------------------- 카카오 로그인 api key, redirecturi --------------------
    @GetMapping("kakaoKey")
    public Map<String, String> kakaoKey() {
        return Map.of("key", RestApiKey, "redirect", redirectUri);
    }

    KakaoAPI kakaoApi = new KakaoAPI();
    @PostMapping("kakaoLogin")
    public Map<String, Object> kakaoLogin(@RequestParam("code") String code, HttpSession session) {
        // 1번 인증코드 요청 전달
        String accessToken = kakaoApi.getAccessToken(code);
        // 2번 인증코드로 토큰 전달
        HashMap<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);
        System.out.println("login info : " + userInfo.toString());
//        Map<String, Object> response = new HashMap<>();
        if(userInfo.get("email") != null) {
            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("isKakao", true);
            session.setAttribute("accessToken", accessToken);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("code", userInfo.get(accessToken));
        return response;
    }

    // ---------- 로그아웃 로직 ----------------
    @PostMapping("logout")
    public void logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
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
