package com.example.travelback.toss.controller;

import com.example.travelback.toss.domain.Toss;
import com.example.travelback.toss.service.TossService;
import com.example.travelback.user.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
                       @SessionAttribute (value = "login",required = false) Member login){
        service.save(id, amount, orderId, requested, login);
    }


    @GetMapping("id/{userId}")
    public List<Toss> getId(@PathVariable String userId){
        return service.getId(userId);
    }








}
