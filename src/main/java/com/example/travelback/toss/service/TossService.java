package com.example.travelback.toss.service;

import com.example.travelback.toss.domain.HotelToss;
import com.example.travelback.toss.domain.Toss;
import com.example.travelback.toss.domain.TransToss;
import com.example.travelback.toss.mapper.TossMapper;
import com.example.travelback.user.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TossService {

    private final TossMapper mapper;

    // 운송 상품 결제 저장
    public void transSave(TransToss transToss,
                          Member login) {
        mapper.transSave(transToss, login.getUserId());
    }

    // 호텔 상품 결제 저장
    public void hotelSave(HotelToss hotelToss, Member login) {
        mapper.hotelSave(hotelToss, login.getUserId());
    }

    // 결제한 운송, 호텔 상품 모두 조회 시키기
    public Map<String, Object> getTransAndHotelTossByUserId(String userId) {
        Map<String, Object> payList = new HashMap<>();


        if (userId.equals("admin")) {
            // 조회 유저가 어드민 일 경우
            payList.put("transToss", mapper.getTransTossAll(userId));
            payList.put("hotelToss", mapper.getHotelTossAll(userId));

            return payList;
        }
        // 조회한 유저당 보여줄 것
        payList.put("transToss", mapper.getTransTossByUserId(userId));
        payList.put("hotelToss", mapper.getHotelTossByUserId(userId));

        return payList;
    }

    // ------------------- 운송상품 예약번호 저장 로직 -------------------
    public void saveReservationNumber(String tossId, String reservNumber) {
        mapper.saveByTossIdAndUserId(tossId, reservNumber);
    }

    // ------------------- 호텔상품 예약번호 저장 로직 -------------------
    public void saveReservationNumber2(String hotelTossId, String reservNumber) {
        mapper.saveByTossIdAndUserId2(hotelTossId, reservNumber);
    }
}
