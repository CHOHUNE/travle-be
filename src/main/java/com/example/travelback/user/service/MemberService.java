package com.example.travelback.user.service;

import com.example.travelback.user.dto.Auth;
import com.example.travelback.user.dto.Member;
import com.example.travelback.user.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper mapper;

    // -------------------- 회원가입 유효성 검증-------------------
    // TODO : 각 input 마다 정규식표현 넣어야함
    public boolean validate(Member member) {
        if (member == null) {
            return false;
        }
        if (member.getUserId() == null || member.getUserId().isBlank()) {
            return false;
        }
        if (member.getUserPassword() == null || member.getUserPassword().isBlank()) {
            return false;
        }
        if (member.getUserName() == null || member.getUserName().isBlank()) {
            return false;
        }
        if (member.getUserPostCode() == null || member.getUserPostCode().isBlank()) {
            return false;
        }
        if (member.getUserAddress() == null || member.getUserAddress().isBlank()) {
            return false;
        }
        if (member.getUserDetailAddress() == null || member.getUserDetailAddress().isBlank()) {
            return false;
        }
        if (member.getUserPhoneNumber() == null || member.getUserPhoneNumber().isBlank()) {
            return false;
        }
        if (member.getUserEmail() == null || member.getUserEmail().isBlank()) {
            return false;
        }
        return true;
    }

    // -------------------- 회원가입 serivce --------------------
    public boolean insert(Member member) {
        return mapper.add(member) == 1;
    }


    // -------------------- 로그인 service --------------------
    public boolean login(Member member, WebRequest request) {
        Member dbMember = mapper.selectById(member.getUserId());

        if (dbMember != null) {
            if (dbMember.getUserPassword().equals(member.getUserPassword())) {
                List<Auth> auth = mapper.selectAuthById(member.getUserId());
                dbMember.setAuth(auth);
                dbMember.setUserPassword("");
                request.setAttribute("login", dbMember, RequestAttributes.SCOPE_SESSION);
                return true;
            }
        }
        return false;
    }
    public Member getMember(String userId) {
        return mapper.selectById(userId);
    }


    // -------------------- id 중복체크 service --------------------
    public String getUserId(String userId) {
        return mapper.selectId(userId);
    }


    // -------------------- 관리자 권한 --------------------
    public boolean hasAccess(String userId, Member login) {
        if (isAdmin(login)) {
            return true;
        }
        return login.getId().equals(userId);
    }
    private boolean isAdmin(Member login) {
        if (login.getAuth() != null) {
            return login.getAuth()
                    .stream()
                    .map(e -> e.getName())
                    .anyMatch(n -> n.equals("admin"));
        }
        return false;
    }


}
