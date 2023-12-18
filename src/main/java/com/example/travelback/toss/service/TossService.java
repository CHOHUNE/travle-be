package com.example.travelback.toss.service;

import com.example.travelback.toss.domain.Toss;
import com.example.travelback.toss.domain.TransToss;
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

    // 운송 상품 결제 저장
    public void transSave(TransToss transToss,
                          Member login) {
        mapper.transSave(transToss, login.getUserId());
    }

    // 결제한 운송 상품 조회
    public List<TransToss> getTransTossByUserId(String userId) {
        if (userId.equals("admin")) {
            // 조회 유저가 어드민 일 경우
            return mapper.getTransTossAll(userId);
        }
        // 조회한 유저당 보여줄 것
        return  mapper.getTransTossByUserId(userId);
    }

    public void saveReservationNumber(String tossId, String reservNumber) {
        System.out.println("tossId = " + tossId);
        System.out.println("reservNumber = " + reservNumber);

        mapper.saveByTossIdAndUserId(tossId, reservNumber);
    }

/*
    public void order(Toss toss) {
        mapper.order(toss);
    }

 */
}
