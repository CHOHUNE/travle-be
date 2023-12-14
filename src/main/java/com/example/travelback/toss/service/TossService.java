package com.example.travelback.toss.service;

import com.example.travelback.toss.mapper.TossMapper;
import com.example.travelback.user.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TossService {

    private final TossMapper mapper;

    public void save(Integer id, Integer amount, String orderId, Member login) {
       login.getUserId();
        mapper.save(id,amount,orderId,login.getUserId());
    }

/*
    public void order(Toss toss) {
        mapper.order(toss);
    }

 */
}
