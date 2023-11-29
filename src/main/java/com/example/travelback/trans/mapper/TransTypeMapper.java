package com.example.travelback.trans.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransTypeMapper {

    @Insert("""
            INSERT INTO transtype (tId, typeName)
            VALUES (#{tId}, #{type})
        """)

    void insert(Integer tId, String type);

}
