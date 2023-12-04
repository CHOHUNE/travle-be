package com.example.travelback.trans.controller;

import com.example.travelback.trans.dto.Trans;
import com.example.travelback.trans.service.TransService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transport")
public class TransController {
    private final TransService service;

    @PostMapping("/add")
    public void add (Trans trans,
                     @RequestParam(value = "transMainImage", required = false) MultipartFile transMainImage,
                     @RequestParam(value = "type") String type) throws IOException {
        if (transMainImage != null){
            System.out.println("transMainImage = " + transMainImage.getOriginalFilename());
            System.out.println("transMainImage = " + transMainImage.getSize());
        }
        service.add(trans, type, transMainImage);
    }

    @GetMapping("list")
    public List<Trans> list() {
        return service.list();
    }

    @GetMapping("listPopularBus")
    // transport 페이지 에서 버스 카테고리 중 인기 있는 순으로 4개 씩 조회 되게 하기
    public List<Trans> listPopularBus() {
        return service.listPopularBus();
    }

    @GetMapping("listPopularAir")
    // transport 페이지 에서 비행기 카테고리 중 인기 있는 순으로 4개 씩 조회 되게 하기
    public List<Trans> listPopularAir() {
        return service.listPopularAir();
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
