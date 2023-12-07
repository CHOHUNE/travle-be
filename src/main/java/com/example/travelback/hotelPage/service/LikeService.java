package com.example.travelback.hotelPage.service;


import com.example.travelback.hotelPage.domain.Like;
import com.example.travelback.hotelPage.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeMapper likemapper;

    public Like getLikeById(Integer id){
        return likemapper.getLikeById(id);
    }

    public void insertLike(Like like){
        likemapper.insertLike(like);
    }
    public Like getLikeByUserId(String userId) {
        return likemapper.getLikesByUserId(userId);
    }
    public void deleteLike(Integer id){
        likemapper.deleteLike(id);
    }


    public Like getLikesByHotelId(Integer hotelId) {
        return likemapper.getLikesByHotelId(hotelId);

    }
}

