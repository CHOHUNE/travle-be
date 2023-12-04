package com.example.travelback.trans.mapper;

import com.example.travelback.trans.dto.TransMainImage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MainImageMapper {

    @Insert("""
        INSERT INTO transMainImage (tId, name)
        VALUES (#{tId}, #{fileName})
        """)
    void insert(Integer tId, String fileName);


    @Select("""
        SELECT id, name FROM transmainimage
        WHERE tId = #{tId}
        """)
    TransMainImage selectNameByTId(Integer tId);

}
