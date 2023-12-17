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

    public void save(Integer id, Integer amount, String orderId, String requested, String phoneNumber, Member login) {
        mapper.save(id, amount, orderId, requested, phoneNumber, login.getUserId());
    }

    public List<Toss> getId(String userId) {
        if (userId.equals("admin")) {
            return mapper.getAll(userId);
        }
        return  mapper.getId(userId);
    }

    public void transSave(Integer id, Integer amount, String orderId, String requested, String phoneNumber, Member login) {
        mapper.transSave(id, amount, orderId, requested, phoneNumber, login.getUserId());
    }

/*
    public void order(Toss toss) {
        mapper.order(toss);
    }

 */
}
