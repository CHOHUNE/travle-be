package com.example.travelback.user.controller;

import com.example.travelback.user.dto.Member;
import com.example.travelback.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService service;


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
