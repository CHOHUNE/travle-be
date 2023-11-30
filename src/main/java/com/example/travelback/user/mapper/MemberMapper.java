package com.example.travelback.user.mapper;

import com.example.travelback.user.dto.Auth;
import com.example.travelback.user.dto.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberMapper {

    // ---------- 회원가입 매핑 ----------
    @Insert("""
            INSERT INTO member (
                userId, 
                userPassword, 
                userName, 
                userPostCode, 
                userAddress, 
                userDetailAddress, 
                userPhoneNumber, 
                userEmail) 
             VALUES (
                #{userId},
                #{userPassword},
                #{userName},
                #{userPostCode},
                #{userAddress},
                #{userDetailAddress},
                #{userPhoneNumber},
                #{userEmail})
            """)
    int add(Member member);

    // ---------- 로그인 매핑 ----------
    @Select("""
            SELECT *
            FROM member
            WHERE userId = #{userId}
            """)
    Member selectById(String userId);

    // ---------- 로그인 관리자, 유저 매핑 ----------
    @Select("""
            SELECT id, memberId, name
            FROM auth
            WHERE memberId = #{id}
            """)
    List<Auth> selectAuthById(String id);

    // ---------- ID 중복체크 매핑 ----------
    @Select("""
            SELECT userId
            FROM member
            WHERE userId = #{userId}
            """)
    String selectId(String userId);

}
