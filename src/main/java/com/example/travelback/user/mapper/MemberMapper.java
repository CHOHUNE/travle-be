package com.example.travelback.user.mapper;

import com.example.travelback.user.dto.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {

    @Insert("""
            INSERT INTO member (
                userId, 
                userPassword, 
                userName, 
                userAddress1, 
                userAddress2, 
                userAddress3, 
                userPhoneNumber, 
                userEmail) 
             VALUES (
                #{userId},
                #{userPassword},
                #{userName},
                #{userAddress1},
                #{userAddress2},
                #{userAddress3},
                #{userPhoneNumber},
                #{userEmail})
            """)
    int add(Member member);


    @Select("""
            SELECT userId
            FROM member
            WHERE userId = #{userId}
            """)
    String selectId(String userId);

    @Select("""
            SELECT userId
            
            """)
    Member selectById(String id);
}
