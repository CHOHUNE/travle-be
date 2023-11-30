package com.example.travelback.hotelPage.controller;

import com.example.travelback.hotelPage.service.HotelService;
import com.example.travelback.hotelPage.domain.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
@RequiredArgsConstructor
public class HotelController {


    private final HotelService hotelService;

    @GetMapping
    public ResponseEntity <List<Hotel>> getAllHotels () {
        List<Hotel> hotel = hotelService.getAllHotels();
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }
}
