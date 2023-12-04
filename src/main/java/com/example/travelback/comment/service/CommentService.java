package com.example.travelback.comment.service;

import com.example.travelback.comment.domain.Comment;
import com.example.travelback.comment.mapper.CommentMapper;
import com.example.travelback.user.dto.Member;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {


    private final CommentMapper mapper;

    public boolean add(Comment comment, Member login) {
        comment.setUserId(login.getUserId());
        return mapper.add(comment) ==1 ;

    }
}
