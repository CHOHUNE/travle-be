package com.example.travelback.hotelPage.mapper;

import com.example.travelback.hotelPage.domain.Hotel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface HotelMapper {

    @Insert("""
            INSERT INTO hotel (name, location, description, mainImg, rating, numberOfBed, review)
             
            VALUES (#{name}, #{location}, #{mainImg}, #{description}, #{rating}, #{numberOfBed}, #{review})""")

    void insertHotel(Hotel hotel);

    @Select("SELECT * FROM hotel WHERE hId = #{hid}")
    Hotel selectHotelById(Long id);

    @Select("""
SELECT * FROM hotel
""")
    List<Hotel> selectAllHotels();


    // 다른 메서드들...
}
