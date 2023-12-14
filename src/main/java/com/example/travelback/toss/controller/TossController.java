package com.example.travelback.toss.controller;

import com.example.travelback.toss.domain.Toss;
import com.example.travelback.toss.service.TossService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/toss")
public class TossController {

    private final TossService service;

    @PostMapping("save")
    public  void save(@RequestBody Toss toss){
        System.out.println("들어옴");
        System.out.println("toss = " + toss);
                service.save(toss);
    }



}
