package com.example.travelback.trans.mapper;

import com.example.travelback.trans.dto.Trans;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TransMapper {
    @Insert("""
        INSERT INTO transport (transStartDay, transTitle, transPrice, transContent) 
        VALUES (#{transStartDay}, #{transTitle}, #{transPrice}, #{transContent}) 
        """)
    @Options(useGeneratedKeys = true, keyProperty = "tId")
    Integer insert(Trans trans);

    @Select("""
        SELECT * FROM transport
        """)
    List<Trans> selectAll();

    @Select("""
        SELECT * FROM transport
        WHERE tId = #{id}
        """)
    Trans selectByTId(Integer id);

    @Update("""
        UPDATE transport
        SET 
            transTitle = #{transTitle},
            transStartDay = #{transStartDay},
            transPrice = #{transPrice},
            transContent = #{transContent}
        WHERE tId = #{tId}
        """)
    void update(Trans trans);

    @Delete("""
        DELETE FROM transport
        WHERE tId = #{id}
        """)
    void deleteById(Integer id);
}
