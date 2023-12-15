package com.example.travelback.hotelPage.service;


import com.example.travelback.hotelPage.domain.Hotel;
import com.example.travelback.hotelPage.domain.Reservation;
import com.example.travelback.hotelPage.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationMapper reservationMapper;


    public void addResrvation(Reservation reservation) {
        reservationMapper.getReservation(reservation);
    }

    public Reservation getReservationById(long id){
        return reservationMapper.getReservationById(id);
    }
}
