package com.example.travelback.user.mapper;

import com.example.travelback.user.dto.Auth;
import com.example.travelback.user.dto.Member;
import org.apache.ibatis.annotations.*;
import retrofit2.http.DELETE;

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

    @Select("""
            SELECT userId, userName, userPassword, userEmail, userPhoneNumber, inserted
            FROM member
            ORDER BY inserted DESC
            LIMIT #{from}, 5;
            """)
    List<Member> selectAll(Integer from);

    @Delete("""
            DELETE FROM member
            WHERE userId = #{userId}
            """)
    int deleteById(String userId);

    @Select("""
            SELECT userEmail
            FROM member
            WHERE userEmail = #{userEmail}
            """)
    String selectEmail(String userEmail);

    @Update("""
            UPDATE member
            SET
                userPassword = #{userPassword},
                userEmail = #{userEmail},
                userPhoneNumber = #{userPhoneNumber}
            WHERE userId = #{userId}
            """)
    int update(Member member);

    @Select("""
            SELECT COUNT(*)
            FROM member;
            """)
    int countAll();

    @Select("""
            SELECT userId
            FROM member
            WHERE userId = #{userId};
            """)
    String selectByUserId(Member member);

    @Update("""
            UPDATE member
            SET
                userPassword = #{userPassword}
            WHERE userId = #{userId}
            """)
    int changePw(Member member);

    @Select("""
            SELECT userName
            FROM member
            WHERE userName = #{userName}
            """)
    String selectByUserName(Member member);
}
