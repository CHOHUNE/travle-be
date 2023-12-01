package com.example.travelback.hotelPage.service;


import com.example.travelback.hotelPage.domain.Hotel;
import com.example.travelback.hotelPage.mapper.HotelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelMapper hotelMapper;

    public void addHotel(Hotel hotel) {
        hotelMapper.insertHotel(hotel);
    }

    public void deleteHotel(Integer id){
        hotelMapper.deleById(id);
    }

    public Hotel getHotelById(Long id) {
        return hotelMapper.selectHotelById(id);
    }

    public List<Hotel> getAllHotels() {
        return hotelMapper.selectAllHotels();
    }

    public void update(Hotel hotel) {
        hotelMapper.update(hotel);
    }
}
