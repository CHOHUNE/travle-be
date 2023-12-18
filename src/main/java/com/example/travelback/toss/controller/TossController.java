package com.example.travelback.toss.controller;

import com.example.travelback.toss.domain.Toss;
import com.example.travelback.toss.domain.TransToss;
import com.example.travelback.toss.service.TossService;
import com.example.travelback.user.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/toss")
public class TossController {

    private final TossService service;

    @PostMapping("save")
    public  void save( Integer id,
                       Integer amount,
                       String orderId,
                       String requested,
                       String phoneNumber,
                       @SessionAttribute (value = "login",required = false) Member login){
        service.save(id, amount, orderId, requested, phoneNumber, login);
    }

// 이거 주석 풀면 에러남
//    @GetMapping("id/{userId}")
//    public List<Toss> getId(@PathVariable String userId){
//        return service.getId(userId);
//    }


    // 운송 상품 결제 저장
    @PostMapping("transSave")
    public void transSave(TransToss transToss,
                        @SessionAttribute (value = "login",required = false) Member login){
        System.out.println("transToss = " + transToss);
        service.transSave(transToss, login);
    }

    // 결제한 운송 상품 조회
    @GetMapping("id/{userId}")
    public List<TransToss> getTransTossByUserId(@PathVariable String userId){
        return service.getTransTossByUserId(userId);
    }




}
