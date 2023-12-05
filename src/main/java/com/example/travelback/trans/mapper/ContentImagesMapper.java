package com.example.travelback.trans.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContentImagesMapper {
    @Insert("""
        INSERT INTO transcontentimages (tId, name, url) 
        VALUES (#{tId}, #{name}, #{url})
        """)
    void insert(Integer tId, String name, String url);
}
