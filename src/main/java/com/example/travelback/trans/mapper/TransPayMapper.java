package com.example.travelback.trans.mapper;

import com.example.travelback.trans.dto.Trans;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TransPayMapper {
    @Select("""
        SELECT 
                tp.tId,
                tp.transTitle, 
                tp.transPrice, 
                tp.transContent, 
                tp.transStartLocation,
                tp.transArriveLocation,
                tp.transAddress,
                tp.transInserted, 
                tty.typeName,
                tMI.url  
            FROM transport tp JOIN transtype tty 
            ON tp.tId = tty.tId
            LEFT JOIN transMainImage tMI on tp.tId = tMI.tId
            WHERE tp.tId = #{id}
        """)
    Trans getTransPayById(Integer id);
}
