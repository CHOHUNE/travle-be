package com.example.travelback.hotelPage.mapper;

import com.example.travelback.hotelPage.domain.Hotel;
import com.example.travelback.hotelPage.domain.Reservation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReservationMapper {

    @Insert(
            """
            
            INSERT INTO hotelreservation (hId, guestName, checkinDate, checkoutDate, numberOfGuests, isConfirmed, createdAt)
            VALUES (#{hId}, #{guestName}, #{checkinDate}, #{checkoutDate}, #{numberOfGuests}, #{isConfirmed}, #{createdAt})
             
            """
    )
    void insertReservation(Reservation reservation);

    @Select("SELECT * FROM hotel WHERE hId = #{hId}")
    Reservation selectReservationById(Long id);


    // 다른 메서드들...ª
}
