package com.example.travelback.trans.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransTypeMapper {

    @Insert("""
            INSERT INTO transtype (tId, typeName)
            VALUES (#{tId}, #{type})
        """)
    void insert(Integer tId, String type);

    @Delete("""
        DELETE FROM transtype
        WHERE tId = #{tId}
        """)
    void deleteByTId(Integer tId);
}
