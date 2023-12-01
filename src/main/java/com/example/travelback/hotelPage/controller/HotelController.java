package com.example.travelback.hotelPage.controller;


import com.example.travelback.hotelPage.domain.Reservation;
import com.example.travelback.hotelPage.service.HotelService;
import com.example.travelback.hotelPage.domain.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hotel")
@RequiredArgsConstructor
public class HotelController {


    private final HotelService hotelService;

    @PostMapping("/write")
    public void add(@RequestBody Hotel hotel){
        hotelService.addHotel(hotel);
    }

    @GetMapping
    public ResponseEntity <List<Hotel>> getAllHotels () {
        List<Hotel> hotel = hotelService.getAllHotels();
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    @GetMapping("/reserv/id/{id}" )
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id);
        return ResponseEntity.ok(hotel);
    }
    @GetMapping("/edit/id/{id}" )
    public ResponseEntity<Hotel> getEditHotelById(@PathVariable Long id){
        Hotel hotel=hotelService.getHotelById(id);
        return ResponseEntity.ok(hotel);
    }

    @PutMapping("/edit")
    public void edit(@RequestBody Hotel hotel){
        System.out.println(hotel);
        hotelService.update(hotel);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id){
        hotelService.deleteHotel(id);
    }


    }


