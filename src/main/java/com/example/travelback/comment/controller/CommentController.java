package com.example.travelback.comment.controller;

import com.example.travelback.comment.service.CommentService;
import com.example.travelback.comment.domain.Comment;
import com.example.travelback.user.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment/")
public class CommentController {

    private final CommentService service;

    @RequestMapping("add")
    public ResponseEntity add(@RequestBody Comment comment,
                              @SessionAttribute (value = "login" ,required = false )Member login){
        System.out.println("comment = " + comment);

        if (login==null){
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (service.add(comment,login)){
            return  ResponseEntity.ok().build();
        }else {
        return ResponseEntity.badRequest().build();

        }

    }

}
