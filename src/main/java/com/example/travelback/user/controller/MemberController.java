package com.example.travelback.user.controller;

import com.example.travelback.user.dto.Member;
//import com.example.travelback.user.service.KakaoLoginService;
//import com.example.travelback.user.service.KakaoService;
import com.example.travelback.user.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService service;
//    private final KakaoLoginService kakaoLoginService;
//    private final KakaoService kakaoService;

    @Value("${Rest.api.key}")
    private String RestApiKey;

    @Value("${Redirect.uri}")
    private String redirectUri;

    // -------------------- 회원가입 로직 --------------------
    @PostMapping("signup")
    public ResponseEntity signup(@RequestBody Member member) {
        Map<String, String> map = new HashMap<>();
        if (service.validate(member, map)) {
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

    @GetMapping("login")
    public Member login(@SessionAttribute(value = "login", required = false) Member login) {
        return login;
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
    public ResponseEntity checkId(Member member, String userId) throws InterruptedException {
        Map<String, String> map = new HashMap<>();

        if (service.validateIdCheck(member, map)) {
            if (service.getUserId(userId) == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok().build();
            }
        } else {
            return ResponseEntity.badRequest().body(map);
        }
    }

    // -------------------- 회원 리스트 --------------------
    @GetMapping("list")
    public Map<String, Object> list(@RequestParam(value = "p", defaultValue = "1") Integer page) {
        return service.list(page);
    }

    // -------------------- 회원 정보 보기 --------------------
    @GetMapping
    public ResponseEntity<Member> view(String userId,
                                       @SessionAttribute(value = "login", required = false) Member login) {

//        if (login == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        if (!service.hasAccess(userId, login)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }

        Member member = service.getMember(userId);

        return ResponseEntity.ok(member);
    }

    // -------------------- 회원 삭제 --------------------
    @DeleteMapping
    public ResponseEntity delete(String userId) {
        if (service.deleteMember(userId)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.internalServerError().build();
    }

    // -------------------- Email 중복체크 --------------------
    @GetMapping(value = "check", params = "userEmail")
    public ResponseEntity checkEmail(String userEmail) {
        if (service.getEmail(userEmail) == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    // -------------------- 회원 정보수정 --------------------
    @PutMapping("edit")
    public ResponseEntity edit(@RequestBody Member member) {
        if (service.update(member)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    // -------------------- pw 찾기 --------------------
    @PostMapping("findPw")
    public ResponseEntity<Member> findPw(@RequestBody Member member, WebRequest request) {
        if (service.validateUserInformationPw(member)) {
            if (service.findPassword(member, request) != null) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    // -------------------- pw 변경 --------------------
    @PutMapping("findPwChange")
    public ResponseEntity findPwChange(@RequestBody Member member, @SessionAttribute("findPasswordUserId") String userId) {
        member.setUserId(userId);
        if (member.getUserPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400 Bad Request
        }
        if (service.changePw(member)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    // -------------------- ID 찾기 --------------------
    @PostMapping("findId")
    public ResponseEntity findId(@RequestBody Member member, WebRequest request) {
        if (service.validateUserInformationId(member)) {
            if (service.findId(member, request) != null) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}















