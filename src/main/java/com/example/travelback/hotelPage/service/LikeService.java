package com.example.travelback.hotelPage.service;


import com.example.travelback.hotelPage.domain.Like;
import com.example.travelback.hotelPage.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeMapper mapper;

    public void update(Like like){
//        첫 좋아요 INSERT
//        두번째 좋아요 DELETE
        int count=0;
        if ( mapper.delete(like)==0){
            count=mapper.insert(like);

        }

    }
}
