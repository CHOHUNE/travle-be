package com.example.travelback.hotelPage.mapper;

import com.example.travelback.hotelPage.domain.Hotel;
import com.example.travelback.hotelPage.domain.Reservation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReservationMapper {


    @Insert("INSERT INTO hotelreservation (hId, guestName, checkinDate, checkoutDate, numberOfGuests, isConfirmed,hrtId,totalPrice) " +
            "VALUES (#{hId}, #{guestName}, #{checkinDate}, #{checkoutDate}, #{numberOfGuests}, #{isConfirmed},#{hrtId},#{totalPrice})")
    void insertReservation(Reservation reservation);

    @Insert("""
            INSERT INTO hotelreservation (hId, guestName, checkinDate, checkoutDate, numberOfGuests,totalPrice,cellPhoneNumber,plusMessage,roomtype)
            VALUES (#{hId}, #{guestName}, #{checkinDate}, #{checkoutDate}, #{numberOfGuests},#{totalPrice},#{cellPhoneNumber},#{plusMessage},#{roomtype})""")
    void addReservation(Reservation reservation);

    @Select("SELECT * FROM hotelreservation WHERE hrId = #{hrId}")
    Reservation getReservationById(long hrId);

    @Select("SELECT * FROM hotelreservation WHERE hId = #{hId}")
    List<Reservation> getReservationsByHotelId(long hId);

    // 다른 메서드들...ª
}


