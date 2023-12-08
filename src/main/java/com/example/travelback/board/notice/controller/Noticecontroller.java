package com.example.travelback.board.notice.controller;

import com.example.travelback.board.notice.domain.Notice;
import com.example.travelback.board.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notice")
public class Noticecontroller {

    private  final NoticeService service;

    @GetMapping("list")
    public List<Notice> list(Integer type){
      return  service.list(type);
    }


}
