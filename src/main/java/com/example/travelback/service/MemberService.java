package com.example.travelback.service;

import com.example.travelback.dto.Member;
import com.example.travelback.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

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
        if (member.getUserAddress1() == null || member.getUserAddress1().isBlank()) {
            return false;
        }
        if (member.getUserAddress2() == null || member.getUserAddress2().isBlank()) {
            return false;
        }
        if (member.getUserAddress3() == null || member.getUserAddress3().isBlank()) {
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

        return false;
    }

    public Member getMember(String id) {
        return mapper.selectById(id);
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
