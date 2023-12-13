package com.example.travelback.hotelPage.service;


import com.example.travelback.hotelPage.domain.Like;
import com.example.travelback.hotelPage.mapper.LikeMapper;
import com.example.travelback.user.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeMapper likemapper;

    public Like getLikeById(Integer id){
        return likemapper.getLikeById(id);
    }

//    public void insertLike(Like like){
//        likemapper.insertLike(like);
//    }

    public List<Like> getLikeByUserId(String userId) {
        return likemapper.getLikesByUserId(userId);
    }
//    public void deleteLike(Integer id){
//        likemapper.deleteLike(id);
//    }


    public Like getLikesByHotelId(Integer hotelId) {
        return likemapper.getLikesByHotelId(hotelId);

    }

    public Object update(Like like, Member login) {
        like.setUserId(login.getUserId());
//      login  세션에 있는 로그인 정보를 userId로 삽입
        if(likemapper.deleteLike(like)==0){
            likemapper.insertLike(like,login);

        }
        return null;
    }

}

