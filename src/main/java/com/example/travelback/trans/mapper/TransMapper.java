package com.example.travelback.trans.mapper;

import com.example.travelback.trans.dto.Trans;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TransMapper {
    @Insert("""
        INSERT INTO transport (
            transTitle,
            transPrice,
            transInsertPrice,
            transContent,
            transStartLocation,
            transArriveLocation,
            transAddress,
            transStartDate,
            transEndDate
             ) 
        VALUES ( 
            #{transTitle},
            #{transPrice},
            #{transInsertPrice},
            #{transContent}, 
            #{transStartLocation}, 
            #{transArriveLocation}, 
            #{transAddress},
            #{transStartDate},
            #{transEndDate}) 
        """)
    @Options(useGeneratedKeys = true, keyProperty = "tId")
    Integer insert(Trans trans);

    // 타입 관계없이 다 조회 지금은 사용 하지 않음
//    @Select("""
//        SELECT
//                tp.tId,
//                tp.transTitle,
//                tp.transPrice,
//                tp.transContent,
//                tp.transStartLocation,
//                tp.transArriveLocation,
//                tp.transAddress,
//                tp.transInserted,
//                tty.typeName,
//                tMI.url
//            FROM transport tp JOIN transtype tty
//            ON tp.tId = tty.tId
//            LEFT JOIN transMainImage tMI on tp.tId = tMI.tId
//            ORDER BY tp.tId
//            LIMIT #{from}, 8
//        """)
//    List<Trans> selectAll(String type, int from);


    @Select("""
        SELECT 
                tp.tId,
                tp.transTitle, 
                tp.transPrice, 
                tp.transInsertPrice,
                tp.transContent, 
                tp.transStartLocation,
                tp.transArriveLocation,
                tp.transAddress,
                tp.transInserted, 
                tp.transStartDate,
                tp.transEndDate,
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
                tp.transTitle, 
                tp.transPrice, 
                tp.transInsertPrice,
                tp.transContent, 
                tp.transStartLocation,
                tp.transArriveLocation,
                tp.transAddress,
                tp.transStartDate,
                tp.transEndDate,
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
                tp.transTitle,
                tp.transPrice,
                tp.transInsertPrice,
                tp.transContent,
                tp.transStartLocation,
                tp.transArriveLocation,
                tp.transAddress,
                tp.transStartDate,
                tp.transEndDate,
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
            transPrice = #{transPrice},
            transInsertPrice = #{transInsertPrice},
            transContent = #{transContent},
            transAddress = #{transAddress},
            transStartDate = #{transStartDate},
            transEndDate = #{transEndDate}
        WHERE tId = #{tId}
        """)
    void update(Trans trans);

    @Delete("""
        DELETE FROM transport
        WHERE tId = #{id}
        """)
    void deleteById(Integer id);


    @Select("""
    SELECT COUNT(*) FROM transtype WHERE typeName = #{type}
    """)
    int countByTypeName(String type);

    @Select("""
        SELECT COUNT(*) 
        FROM transtype tt 
        LEFT JOIN transport tp 
        ON tt.tId = tp.tId
        WHERE tt.typeName = #{type} 
        AND tp.transTitle 
        LIKE CONCAT('%', #{keyword}, '%')
        """)
    int countByTypeNameAndKeyword(String type, String keyword);

    @Select("""
    SELECT 
         tp.tId,
         tp.transTitle,
         tp.transPrice,
         tp.transInsertPrice,
         tp.transContent,
         tp.transStartLocation,
         tp.transArriveLocation,
         tp.transAddress,
         tp.transStartDate,
         tp.transEndDate,
         tp.transInserted,
         tty.typeName,
         tMI.url
        FROM transport tp JOIN transtype tty 
        ON tp.tId = tty.tId
        LEFT JOIN transMainImage tMI on tp.tId = tMI.tId
        WHERE typeName = #{type}
        ORDER BY tp.tId DESC 
        LIMIT #{from}, #{pageSize}
""")
    List<Trans> selectAllByTypeName(String type, int from, int pageSize);

    @Select("""
        SELECT 
                tp.tId,
                tp.transTitle, 
                tp.transPrice, 
                tp.transInsertPrice,
                tp.transContent, 
                tp.transStartLocation,
                tp.transArriveLocation,
                tp.transAddress,
                tp.transInserted, 
                tp.transStartDate,
                tp.transEndDate,
                tty.typeName,
                tMI.url  
            FROM transport tp JOIN transtype tty 
            ON tp.tId = tty.tId
            LEFT JOIN transMainImage tMI on tp.tId = tMI.tId
            WHERE tty.typeName = 'bus'
            ORDER BY tp.tId DESC 
            LIMIT 8;
        """)
    List<Trans> selectPopularToBusEight();

    @Select("""
        SELECT 
                tp.tId,
                tp.transTitle, 
                tp.transPrice, 
                tp.transInsertPrice,
                tp.transContent, 
                tp.transStartLocation,
                tp.transArriveLocation,
                tp.transAddress,
                tp.transStartDate,
                tp.transEndDate,
                tp.transInserted, 
                tty.typeName,
                tMI.url
            FROM transport tp JOIN transtype tty 
            ON tp.tId = tty.tId
            LEFT JOIN transMainImage tMI on tp.tId = tMI.tId
            WHERE tty.typeName = 'air'
            ORDER BY tp.tId DESC 
            LIMIT 8;
        """)
    List<Trans> selectPopularToAirEight();

    @Select("""
    SELECT 
         tp.tId,
         tp.transTitle,
         tp.transPrice,
         tp.transInsertPrice,
         tp.transContent,
         tp.transStartLocation,
         tp.transArriveLocation,
         tp.transAddress,
         tp.transStartDate,
         tp.transEndDate,
         tp.transInserted,
         tty.typeName,
         tMI.url
        FROM transport tp JOIN transtype tty 
        ON tp.tId = tty.tId
        LEFT JOIN transMainImage tMI on tp.tId = tMI.tId
        WHERE typeName = #{type} AND tp.transTitle LIKE CONCAT('%', #{keyword}, '%')
        ORDER BY tp.tId DESC 
        LIMIT #{from}, #{pageSize}
    """)
    List<Trans> selectAllByTypeNameAndKeyword(String type, int from, int pageSize, String keyword);


}
