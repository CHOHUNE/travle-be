package com.example.travelback.user.service;

import com.example.travelback.user.dto.Auth;
import com.example.travelback.user.dto.Member;
import com.example.travelback.user.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper mapper;
//    private final PasswordEncoder passwordEncoder;

    // -------------------- 회원가입 유효성 검증-------------------
    // TODO : 각 input 마다 정규식표현 넣어야함
    public boolean validate(Member member, Map<String, String> map) {
        if (member == null) {
            return false;
        }
        if (member.getUserId() == null || member.getUserId().isBlank()) {
            map.put("message", "아이디를 다시 입력해주시기 바랍니다.");
            return false;
        }
        if (member.getUserPassword() == null || member.getUserPassword().isBlank()) {
            map.put("message", "비밀번호를 다시 입력해주시기 바랍니다.");
            return false;
        }
        if (member.getUserName() == null || member.getUserName().isBlank()) {
            map.put("message", "이름을 다시 입력해주시기 바랍니다.");
            return false;
        }
        if (member.getUserPostCode() == null || member.getUserPostCode().isBlank()) {
            map.put("message", "주소를 다시 입력해주시기 바랍니다.");
            return false;
        }
        if (member.getUserAddress() == null || member.getUserAddress().isBlank()) {
            map.put("message", "주소를 다시 입력해주시기 바랍니다.");
            return false;
        }
        if (member.getUserDetailAddress() == null || member.getUserDetailAddress().isBlank()) {
            map.put("message", "주소를 다시 입력해주시기 바랍니다.");
            return false;
        }
        if (member.getUserPhoneNumber() == null || member.getUserPhoneNumber().isBlank()) {
            map.put("message", "핸드폰번호를 다시 입력해주시기 바랍니다.");
            return false;
        }
        if (member.getUserEmail() == null || member.getUserEmail().isBlank()) {
            map.put("message", "이메일을 다시 입력해주시기 바랍니다.");
            return false;
        }
        return true;
    }

    // -------------------- 회원가입 serivce --------------------
    public boolean insert(Member member) {

        // 회원가입 시 비밀번호 암호화
//        String encryptedPassword = passwordEncoder.encrypt(member.getUserEmail(), member.getUserPassword());
//        member.setUserPassword(encryptedPassword);


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


    public String getLoggedInUserId(WebRequest request) {
        Member login = (Member) request.getAttribute("login", RequestAttributes.SCOPE_SESSION);
        return login != null ? login.getUserId() : null;
    }


    public Map<String, Object> list(Integer page) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> pageInfo = new HashMap<>();

        int countAll = mapper.countAll();
        int lastPageNumber = (countAll - 1) / 5 + 1;
        int startPageNumber = (page - 1) / 5 * 5 + 1;
        int endPageNumber = startPageNumber + 4;
        endPageNumber = Math.min(endPageNumber, lastPageNumber);

        pageInfo.put("startPageNumber", startPageNumber);
        pageInfo.put("lastPageNumber", lastPageNumber);

        int from = (page - 1) * 5;
        map.put("memberList", mapper.selectAll(from));
        map.put("pageInfo", pageInfo);

        return map;
    }

    public boolean deleteMember(String userId) {
        return mapper.deleteById(userId) == 1;
    }

    public String getEmail(String userEmail) {
        return mapper.selectEmail(userEmail);
    }

    public boolean update(Member member) {
        Member oldMember = mapper.selectById(member.getUserId());
        if (member.getUserPassword().equals("")) {
            member.setUserPassword(oldMember.getUserPassword());
        }
        return mapper.update(member) == 1;
    }

    // 비밀번호 찾기
    // 회원가입할때 해당 아이디에 맞는 이름이랑, 핸드폰번호랑 비교하는 로직
    public boolean validateUserInformationPw(Member member) {
        String userIdFromDb = mapper.selectByUserName(member.getUserName(), member.getUserPhoneNumber());
        return userIdFromDb != null && userIdFromDb.equals(member.getUserId());
    }

    public String findPassword(Member member, WebRequest request) {
        String s = mapper.selectByUserId(member);

        if (s != null) {
            request.setAttribute("findPasswordUserId", s, RequestAttributes.SCOPE_SESSION);
        }
        return s;

    }

    public boolean changePw(Member member) {
        return mapper.changePw(member) == 1;
    }


    public boolean validateUserInformationId(Member member) {
        String userName = mapper.selectByUserName2(member.getUserName(), member.getUserPhoneNumber());
        return userName != null && userName.equals(member.getUserName());
    }

    public String findId(Member member, WebRequest request) {
        String b = mapper.selectFindName(member);

        if (b != null) {
            request.setAttribute("findIdUserName", b, RequestAttributes.SCOPE_SESSION);
        }
        return b;
    }

    public boolean validateIdCheck(Member member) {
        if (member == null) {
            return false;
        }
        if (member.getUserId() == null || member.getUserId().isBlank()) {
            return false;
        }
        return true;
    }
}
