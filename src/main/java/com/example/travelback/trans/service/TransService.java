package com.example.travelback.trans.service;

import com.example.travelback.trans.dto.Trans;
import com.example.travelback.trans.mapper.TransMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransService {
    private final TransMapper mapper;

    public void add(Trans trans) {
        mapper.insert(trans);
    }

    public List<Trans> list() {
        return mapper.selectAll();
    }

    public Trans get(Integer id) {
        return mapper.selectByTId(id);
    }

    public void update(Trans trans) {
        mapper.update(trans);
    }
}
