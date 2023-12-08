package com.example.travelback.board.notice.service;

import com.example.travelback.board.notice.domain.Notice;
import com.example.travelback.board.notice.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private  final NoticeMapper mapper;


    public List<Notice> list(Integer type) {
        return mapper.list(type);
    }
}
