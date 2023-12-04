package com.example.travelback.trans.mapper;

import com.example.travelback.trans.dto.TransMainImage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MainImageMapper {

    @Insert("""
        INSERT INTO transMainImage (tId, name, url)
        VALUES (#{tId}, #{fileName}, #{url})
        """)
    void insert(Integer tId, String fileName, String url);


    @Select("""
        SELECT id, name FROM transmainimage
        WHERE tId = #{tId}
        """)
    TransMainImage selectNameByTId(Integer tId);

    @Delete("""
        DELETE FROM transmainimage
        WHERE tId = #{tId}
        """)
    void deleteByTId(Integer tId);
}
