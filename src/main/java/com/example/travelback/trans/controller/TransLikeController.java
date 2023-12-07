package com.example.travelback.trans.controller;

import com.example.travelback.trans.dto.TransLike;
import com.example.travelback.trans.service.TransLikeService;
import com.example.travelback.user.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transLike")
public class TransLikeController {
    private final TransLikeService service;

    @PostMapping
    public ResponseEntity like(@RequestBody TransLike transLike,
                               @SessionAttribute(value = "login",required = false) Member login) {
        if (login == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        service.update(transLike, login);
        return null;
    }
}
