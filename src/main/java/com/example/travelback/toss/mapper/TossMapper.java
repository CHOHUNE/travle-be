package com.example.travelback.toss.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TossMapper {

    @Insert("""
                insert into travel.ttoss (id, amount,orderId,userId ) values (#{id},#{amount},#{orderId} ,#{userId});
        """)
    int save(Integer id, Integer amount, String orderId, String userId);


}

