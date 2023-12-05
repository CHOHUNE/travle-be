package com.example.travelback.hotelPage.mapper;

import com.example.travelback.hotelPage.domain.Hotel;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface HotelMapper {

    @Insert("""
            INSERT INTO hotel (hId, name, location, description, mainImg, rating, numberOfBed, review, roomType, subImg1, subImg2, mapImg, numberOfBedRooms, totalPrice)
             
            VALUES (#{hid}, #{name}, #{location}, #{description}, #{mainImg}, #{rating}, #{numberOfBed}, #{review}, #{roomType}, #{subImg1}, #{subImg2}, #{mapImg}, #{numberOfBedRooms}, #{totalPrice})""")

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

    void deleById(Integer id);

@Update("""
        UPDATE hotel
        SET 
            name =#{name},
            location=#{location},
            description=#{description},
            mainImg=#{mainImg},
            numberOfBed=#{numberOfBed},
            roomType=#{roomType},
            subImg1=#{subImg1},
            subImg2=#{subImg2},
            mapImg=#{mapImg},
            totalPrice=#{totalPrice},
            numberOfBedRooms=#{numberOfBedRooms}  
            
            WHERE hId=#{hid}
           """)
    void update(Hotel hotel);


    // 다른 메서드들...
}
