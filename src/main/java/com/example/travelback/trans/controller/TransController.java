package com.example.travelback.trans.controller;

import com.example.travelback.trans.dto.Trans;
import com.example.travelback.trans.dto.TransType;
import com.example.travelback.trans.service.TransService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transport")
public class TransController {
    private final TransService service;

    @PostMapping("/add")
    public void add (@RequestBody Trans trans,
                     @RequestParam(name = "type", defaultValue = "bus") String type) {
        System.out.println(trans);
        System.out.println(type);
        service.add(trans, type);
    }

    @GetMapping("list")
    public List<Trans> list() {
        // TODO : transport의 컬럼에 type을 추가 하고 type에 맞게 조회 시키기
        return service.list();
    }

    @GetMapping("id/{id}")
    public Trans get(@PathVariable Integer id) {
        return service.get(id);
    }

    @PutMapping("edit")
    public void edit (@RequestBody Trans trans) {
        service.update(trans);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
