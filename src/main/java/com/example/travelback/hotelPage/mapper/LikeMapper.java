package com.example.travelback.hotelPage.mapper;

import com.example.travelback.hotelPage.domain.Like;
import com.example.travelback.user.dto.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LikeMapper {

//    @Select("SELECT * FROM hotelike WHERE id = #{id}")
//    Like getLikeById(Integer id);
//
//    @Select("SELECT * FROM hotelike WHERE hid = #{hotelId}")
//    Like getLikesByHotelId(Integer hotelId);
//
    @Select("SELECT * FROM hotelike WHERE userId=#{userId} ORDER BY createdAt DESC")
    List<Like> getLikesByUserId(String userId);

    @Select("SELECT * FROM hotelike WHERE userId=#{userId} AND hid=#{hotelId}")
    List<Like> getLikesByUserIdAndHotelId(Integer hotelId,String userId);

    //    @Delete("""
//    DELETE FROM hotelike WHERE hid=#{like.hotelId} AND userId=#{id}
//""")
//    void deleteLikeByHotelId(Like like, String id);

//
//    @Delete("DELETE FROM hotelike WHERE hid = #{hid}")
//    void deleteLike(Integer id);

    @Insert("INSERT INTO hotelike (hid, userId,name,mainImgUrl,location,lodgingType) VALUES (#{like.hotelId}, #{login.userId},#{like.name},#{like.mainImgUrl},#{like.location},#{like.lodgingType})")
    int insertLike(Like like, Member login);

    @Delete("""
    DELETE FROM hotelike WHERE hid=#{hotelId} AND userId=#{userId}
""")
    int deleteLike(Like like);

    @Delete("""
DELETE FROM hotelike WHERE hid=#{id}
""")
    void deleteLikeById(Integer id);



}
