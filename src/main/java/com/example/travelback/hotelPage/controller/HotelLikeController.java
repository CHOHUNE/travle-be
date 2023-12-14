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

//    @GetMapping("/{id}")
//    public Like getLikeById(@PathVariable Integer id) {
//        return service.getLikeById(id);
//    }
//
//    @GetMapping("/hotel/{hotelId}")
//    public Like getLikesByHotelId(@PathVariable Integer hotelId) {
//        return service.getLikesByHotelId(hotelId);
//    }


    @PostMapping
    public ResponseEntity insertLike(@RequestBody Like like,
                           @SessionAttribute(value ="login",required = false)Member login){
        if(login ==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(service.update(like,login));
    }

//    @GetMapping("/user")
//    public ResponseEntity get(
//            Integer hotelId,
//            @SessionAttribute(value = "login",required = false)Member login){
//        return ResponseEntity.ok(service.get(hotelId,login));
//    }

}



