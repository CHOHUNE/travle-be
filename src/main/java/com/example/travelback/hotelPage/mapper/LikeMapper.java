package com.example.travelback.hotelPage.mapper;

import com.example.travelback.hotelPage.domain.Like;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeMapper {
    @Delete("""
DELETE FROM hotelLike
WHERE hId=#{hId}
AND userId=#{userId}
""")
    int delete(Like like);

    @Insert("""
         INSERT INTO hotelLik (hId,userId)   
         VALUES (#{hID},#{userId})
            """)
    int insert(Like like);


}
