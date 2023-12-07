/*
package com.example.travelback.board.boardLike.service;

import com.example.travelback.board.boardLike.domain.BoardLike;
import com.example.travelback.board.boardLike.mapper.BoardLikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardLikeService {


    private final BoardLikeMapper mapper;

    public void like(BoardLike boardLike) {

        int count=0;
        if(mapper.delete(boardLike)==0){
            count=mapper.like(boardLike);
        }

    }
}
*/