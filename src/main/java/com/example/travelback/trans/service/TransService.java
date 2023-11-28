package com.example.travelback.trans.service;

import com.example.travelback.trans.dto.Trans;
import com.example.travelback.trans.mapper.TransMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransService {
    private final TransMapper mapper;

    public void add(Trans trans) {
        mapper.insert(trans);
    }
}
