package com.example.travelback.trans.mapper;

import com.example.travelback.trans.dto.TransBucket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TransBucketMapper {

    @Select("""
        SELECT
            m.userId,
            tt.typeName,
            timage.url,
            t.transTitle,
            t.transStartLocation,
            t.transArriveLocation,
            t.transAddress,
            tl.createdAt
        FROM transport t JOIN translike tl
        ON t.tId = tl.transId
        JOIN transtype tt
        ON t.tId = tt.tId
        JOIN transmainimage timage
        ON t.tId = timage.tId
        JOIN member m
        ON tl.userId = m.userId
        WHERE m.userId = #{usesrId};
        """)
    List<TransBucket> selectByUserId(String userId);
}
