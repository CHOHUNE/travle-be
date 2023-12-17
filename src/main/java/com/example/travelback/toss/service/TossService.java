package com.example.travelback.toss.service;

import com.example.travelback.toss.domain.Toss;
import com.example.travelback.toss.mapper.TossMapper;
import com.example.travelback.user.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TossService {

    private final TossMapper mapper;

    public void save(Integer id, Integer amount, String orderId, String requested, Member login) {
        mapper.save(id, amount, orderId, requested, login.getUserId());
    }

    public List<Toss> getId(String userId) {
        return  mapper.getId(userId);
    }

/*
    public void order(Toss toss) {
        mapper.order(toss);
    }

 */
}
