package com.example.travelback.hotelPage.service;


import com.example.travelback.hotelPage.domain.Hotel;
import com.example.travelback.hotelPage.mapper.HotelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelMapper hotelMapper;

    public List<Hotel> getAllHotels(){
        return hotelMapper.selectAllHotels();
    }
}
