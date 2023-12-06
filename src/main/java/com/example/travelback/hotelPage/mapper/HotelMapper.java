package com.example.travelback.hotelPage.mapper;

import com.example.travelback.hotelPage.domain.Hotel;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface HotelMapper {

    @Insert("""
            INSERT INTO hotel (hId, name, location, description, rating, numberOfBed, review, roomType, numberOfBedRooms, totalPrice,mainImgUrl)
             
            VALUES (#{hotel.hid}, #{hotel.name}, #{hotel.location}, #{hotel.description}, #{hotel.rating}, #{hotel.numberOfBed}, #{hotel.review}, #{hotel.roomType},  #{hotel.numberOfBedRooms}, #{hotel.totalPrice},#{mainImgUrl})""")
    @Options(useGeneratedKeys = true,keyProperty = "hotel.hid")
    void insertHotel(Hotel hotel, String mainImgUrl);

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
    void deletById(Integer id);

    @Update("""
            UPDATE hotel
            SET 
                name =#{name},
                location=#{location},
                description=#{description},
                numberOfBed=#{numberOfBed},
                roomType=#{roomType},
                totalPrice=#{totalPrice},
                numberOfBedRooms=#{numberOfBedRooms}  
                
                WHERE hId=#{hid}
               """)
    void update(Hotel hotel);

    @Update("""
            UPDATE hotel
            SET mainImg=#{mainImg},mainImgUrl=#{mainImgUrl}
            
            WHERE hId=#{hid}
            """)
    void updateMainImg(long hid, String mainImg,String mainImgUrl);


    // 다른 메서드들...
}
