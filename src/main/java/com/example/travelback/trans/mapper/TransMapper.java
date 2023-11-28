package com.example.travelback.trans.mapper;

import com.example.travelback.trans.dto.Trans;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransMapper {
    @Insert("""
        INSERT INTO transport (transStartDay, transTitle, transPrice, transContent) 
        VALUES (#{transStartDay}, #{transTitle}, #{transPrice}, #{transContent}) 
        """)
    Integer insert(Trans trans);
}
