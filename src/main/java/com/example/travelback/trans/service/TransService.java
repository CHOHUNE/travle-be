package com.example.travelback.trans.service;

import com.example.travelback.trans.dto.Trans;
import com.example.travelback.trans.mapper.TransMapper;
import com.example.travelback.trans.mapper.TransTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransService {
    private final TransMapper mapper;
    private final TransTypeMapper transTypeMapper;

    public void add(Trans trans, String type) {
        mapper.insert(trans);

        transTypeMapper.insert(trans.getTId(), type);
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

    public void delete(Integer id) {
        mapper.deleteById(id);
    }
}
