package com.example.travelback.toss.controller;

import com.example.travelback.toss.domain.Toss;
import com.example.travelback.toss.service.TossService;
import com.example.travelback.user.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/toss")
public class TossController {

    private final TossService service;

    @PostMapping("save")
    public  void save( Integer id,Integer amount,String orderId,
                       @SessionAttribute (value = "login",required = false) Member login){
//        System.out.println("id = " + id);
//        System.out.println("orderId = " + orderId);
//        System.out.println("amount = " + amount);
        service.save(id,amount,orderId ,login);
    }
/*
    @PostMapping("order")
    public  void  order(Toss toss,
                        @RequestParam (value = "tid", required = false) Integer tid){
        System.out.println("과연 ");
        System.out.println("tid = " + tid);
        System.out.println("toss = " + toss);

        service.order(toss);
    }
*/





}
