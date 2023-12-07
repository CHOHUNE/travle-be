package com.example.travelback.trans.controller;

import com.example.travelback.trans.dto.TransLike;
import com.example.travelback.trans.service.TransLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transLike")
public class TransLikeController {
    private final TransLikeService service;

    @PostMapping
    public void like(@RequestBody TransLike transLike) {
        service.update(transLike);
    }
}
