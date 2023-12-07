package com.example.travelback.hotelPage.mapper;

import com.example.travelback.hotelPage.domain.Hotel;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface HotelMapper {

    @Insert("""
            INSERT INTO hotel (name, location, description, rating, numberOfBed, review, roomType, numberOfBedRooms, totalPrice,mainImgUrl)
             
            VALUES (#{name}, #{location}, #{description}, #{rating}, #{numberOfBed}, #{review}, #{roomType},  #{numberOfBedRooms}, #{totalPrice},#{mainImgUrl})""")
    @Options(useGeneratedKeys = true,keyProperty = "hid")
    void insertHotel(Hotel hotel);

    @Select("SELECT * FROM hotel WHERE hId = #{hid}")
    Hotel selectHotelById(Long id);

    @Select("""
            SELECT * FROM hotel
            """)
    List<Hotel> selectAllHotels();

    @Delete("""
            DELETE FROM hotel
            WHERE hId=#{id}
            """)
    void deleteById(Integer id);

    @Update("""
            UPDATE hotel
            SET 
                name =#{name},
                location=#{location},
                description=#{description},
                numberOfBed=#{numberOfBed},
                roomType=#{roomType},
                totalPrice=#{totalPrice},
                numberOfBedRooms=#{numberOfBedRooms},  
                mainImgUrl=#{mainImgUrl}
                
                WHERE hId=#{hid}
               """)
    void update(Hotel hotel);

    @Update("""
            UPDATE hotel
            SET mainImg=#{mainImg},mainImgUrl=#{mainImgUrl},subImgUrl1=#{subImgUrl1},
            subImgUrl2=#{subImgUrl2},mapImgUrl=#{mapImgUrl}
            
            WHERE hId=#{hid}
            """)
    void updateImg(long hid, String mainImg, String mainImgUrl, String subImgUrl1, String subImgUrl2, String mapImgUrl);

    @Select("""
SELECT mainImg
FROM hotel
WHERE hId=#{hid}
""")
    String selectNameByHotelId(Integer hid);
// file delete Mapper

    // 다른 메서드들...
}
