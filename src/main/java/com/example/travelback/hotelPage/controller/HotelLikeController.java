package com.example.travelback.hotelPage.controller;


import com.example.travelback.hotelPage.domain.Hotel;
import com.example.travelback.hotelPage.domain.Like;
import com.example.travelback.hotelPage.service.HotelService;
import com.example.travelback.hotelPage.service.LikeService;
import com.example.travelback.user.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishlist")
public class HotelLikeController {

    private final LikeService service;

    @GetMapping("/{id}")
    public Like getLikeById(@PathVariable Integer id) {
        return service.getLikeById(id);
    }

    @GetMapping("/hotel/{hotelId}")
    public Like getLikesByHotelId(@PathVariable Integer hotelId) {
        return service.getLikesByHotelId(hotelId);
    }

    @PostMapping
    public void insertLike(@RequestBody Like like) {
        service.insertLike(like);
    }

    @DeleteMapping("/{id}")
    public void deleteLike(@PathVariable Integer id) {
        service.deleteLike(id);
    }

}



