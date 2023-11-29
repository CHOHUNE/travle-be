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

        // 운송 상품 삭제전 type 테이블 을 삭제
        transTypeMapper.deleteByTId(id);

        // 운송 상품 삭제 하기
        mapper.deleteById(id);
    }

    public List<Trans> listPopularBus() {
        return mapper.selectPopularToBus();
    }

    public List<Trans> listPopularAir() {
        return mapper.selectPopularToAir();
    }
}
