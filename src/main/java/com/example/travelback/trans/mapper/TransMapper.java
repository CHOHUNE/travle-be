package com.example.travelback.trans.mapper;

import com.example.travelback.trans.dto.Trans;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TransMapper {
    @Insert("""
        INSERT INTO transport (transStartDay, transTitle, transPrice, transContent, transStartLocation, transArriveLocation) 
        VALUES (#{transStartDay}, #{transTitle}, #{transPrice}, #{transContent}, #{transStartLocation}, #{transArriveLocation}) 
        """)
    @Options(useGeneratedKeys = true, keyProperty = "tId")
    Integer insert(Trans trans);

    @Select("""
        SELECT
                tp.tId,
                tp.transStartDay,
                tp.transTitle,
                tp.transPrice,
                tp.transContent,
                tp.transStartLocation,
                tp.transArriveLocation,
                tp.transInserted,
                tty.typeName,
                tMI.url 
            FROM transport tp JOIN transtype tty
            ON tp.tId = tty.tId
            LEFT JOIN transMainImage tMI on tp.tId = tMI.tId
            ORDER BY tp.tId
        """)
    List<Trans> selectAll();

    @Select("""
        SELECT 
                tp.tId,
                tp.transStartDay,
                tp.transTitle, 
                tp.transPrice, 
                tp.transContent, 
                tp.transStartLocation,
                tp.transArriveLocation,
                tp.transInserted, 
                tty.typeName,
                tMI.url  
            FROM transport tp JOIN transtype tty 
            ON tp.tId = tty.tId
            LEFT JOIN transMainImage tMI on tp.tId = tMI.tId
            WHERE tty.typeName = 'bus'
            ORDER BY tp.tId DESC 
            LIMIT 4;
        """)
    List<Trans> selectPopularToBus();

    @Select("""
        SELECT 
                tp.tId,
                tp.transStartDay,
                tp.transTitle, 
                tp.transPrice, 
                tp.transContent, 
                tp.transStartLocation,
                tp.transArriveLocation,
                tp.transInserted, 
                tty.typeName,
                tMI.url
            FROM transport tp JOIN transtype tty 
            ON tp.tId = tty.tId
            LEFT JOIN transMainImage tMI on tp.tId = tMI.tId
            WHERE tty.typeName = 'air'
            ORDER BY tp.tId DESC 
            LIMIT 4;
        """)
    List<Trans> selectPopularToAir();

    @Select("""
            SELECT
                tp.tId,
                tp.transStartDay,
                tp.transTitle,
                tp.transPrice,
                tp.transContent,
                tp.transStartLocation,
                tp.transArriveLocation,
                tp.transInserted,
                tty.typeName,
                tMI.url
            FROM transport tp JOIN transtype tty
            ON tp.tId = tty.tId
            LEFT JOIN transMainImage tMI on tp.tId = tMI.tId
            WHERE tp.tId = #{id}
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
