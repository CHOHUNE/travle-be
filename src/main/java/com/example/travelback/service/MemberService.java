package com.example.travelback.service;

import com.example.travelback.dto.Member;
import com.example.travelback.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper mapper;
    private final PasswordEncoder passwordEncoder;

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

    // 회원가입
    public boolean insert(Member member) {
        member.setUserPassword(passwordEncoder.encode(member.getUserPassword()));
        return mapper.add(member) == 1;
    }
}
