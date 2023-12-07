package com.example.travelback.trans.mapper;

import com.example.travelback.trans.dto.TransContentImages;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ContentImagesMapper {
    @Insert("""
        INSERT INTO transcontentimages (tId, name, url) 
        VALUES (#{tId}, #{name}, #{url})
        """)
    void insert(Integer tId, String name, String url);

    @Select("""
        SELECT * FROM transcontentimages
        WHERE tId = #{tId}
        """)
    List<TransContentImages> selectNameByTId(Integer tId);

    @Delete("""
        DELETE FROM transcontentimages 
        WHERE tId = #{tId}
        """)
    void deleteByTId(Integer tId);
}
