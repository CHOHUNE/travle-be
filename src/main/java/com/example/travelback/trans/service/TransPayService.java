package com.example.travelback.trans.service;

import com.example.travelback.trans.dto.Trans;
import com.example.travelback.trans.mapper.TransPayMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class TransPayService {
    private final TransPayMapper mapper;
    public Trans getTransPayById(Integer id) {

        return mapper.getTransPayById(id);
    }
}
