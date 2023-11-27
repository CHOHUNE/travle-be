package com.example.travelback.mapper;

import com.example.travelback.dto.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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
}
