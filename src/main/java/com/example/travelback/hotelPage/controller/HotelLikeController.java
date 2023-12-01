package com.example.travelback.hotelPage.controller;


import com.example.travelback.hotelPage.domain.Like;
import com.example.travelback.hotelPage.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class HotelLikeController {
    private final LikeService service;

    @PostMapping
    public void like(@RequestBody Like like){
        service.update(like);
    }


}
