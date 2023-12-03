package com.example.travelback.trans.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainImageMapper {

    @Insert("""
        INSERT INTO transMainImage (tId, name)
        VALUES (#{tId}, #{fileName})
        """)
    void insert(Integer tId, String fileName);
}
