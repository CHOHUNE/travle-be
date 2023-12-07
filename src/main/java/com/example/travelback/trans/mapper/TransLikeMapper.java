package com.example.travelback.trans.mapper;

import com.example.travelback.trans.dto.TransLike;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TransLikeMapper {

    @Delete("""
        DELETE FROM translike
        WHERE tId = #{tId}
        AND userId = #{userId}
        """)
    int delete(TransLike transLike);

    @Insert("""
        INSERT INTO translike (tid, userid)
        VALUES (#{tId}, #{userId})
        """)
    int insert(TransLike transLike);

}
