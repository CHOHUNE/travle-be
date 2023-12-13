package com.example.travelback.toss.service;

import com.example.travelback.toss.mapper.TossMapper;
import com.example.travelback.toss.domain.Toss;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TossService {

    private final TossMapper mapper;
    public void save(Toss toss) {
        mapper.save(toss);
    }



}
