package com.example.travelback.hotelPage.service;


import com.example.travelback.hotelPage.domain.Reservation;
import com.example.travelback.hotelPage.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationMapper reservationMapper;

//    @Transactional
//    public boolean saveReservation(Reservation reservation){
//        reservationMapper.insertReservation(reservation);
//        return reservation.getId() != null;
//    }
    public Reservation getReservationById(long id){
        return reservationMapper.selectReservationById(id);
    }
}
