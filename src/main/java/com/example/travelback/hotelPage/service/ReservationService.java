package com.example.travelback.hotelPage.service;


import com.example.travelback.hotelPage.mapper.ResrvationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ResrvationMapper reservationMapper;
    @Transactional
    public boolean saveReservation(Reseervation reseervation){
        return reservationMapper.insertReservation(reseervation)==1;
    }
    public Reservation getReservationById(long id){
        return reservationMapper.selectReservationById(id);
    }
}
