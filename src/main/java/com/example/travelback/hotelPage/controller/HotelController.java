package com.example.travelback.hotelPage.controller;

import com.example.travelback.hotelPage.service.HotelService;
import com.example.travelback.hotelPage.domain.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {
    private HotelService hotelService;

    @GetMapping
    public ResponseEntity<List<Hotel>>getAllHotels(){
        List<Hotel> hotels=hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }

}
