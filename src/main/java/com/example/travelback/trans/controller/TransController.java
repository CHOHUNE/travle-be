package com.example.travelback.trans.controller;

import com.example.travelback.trans.dto.Trans;
import com.example.travelback.trans.dto.TransBucket;
import com.example.travelback.trans.dto.TransLike;
import com.example.travelback.trans.service.TransBucketService;
import com.example.travelback.trans.service.TransLikeService;
import com.example.travelback.trans.service.TransPayService;
import com.example.travelback.trans.service.TransService;
import com.example.travelback.user.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transport")
public class TransController {
    private final TransService service;
    private final TransBucketService transBucketService;
    private final TransPayService transPayService;

    @PostMapping("/add")
    public void add (Trans trans,
                     @RequestParam(value = "transMainImage", required = false) MultipartFile transMainImage,
                     @RequestParam(value = "transContentImages[]", required = false) MultipartFile[] transContentImages,
                     @RequestParam(value = "type") String type) throws IOException {

        service.add(trans, type, transMainImage, transContentImages);
    }
    // 상품 리스트별로 조회하고 페이지 부여
    @GetMapping("list")
    public Map<String, Object> list(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "p",defaultValue = "1")Integer page,
            @RequestParam(value = "k",required = false)String keyword) {

        return service.list(type,page,keyword);
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

    @GetMapping("listPopularBusEight")
    // transport 페이지 에서 버스 카테고리 중 인기 있는 순으로 8개 씩 조회 되게 하기
    public List<Trans> listPopularBusEight() {
        return service.listPopularBusEight();
    }

    @GetMapping("listPopularAirEight")
    // transport 페이지 에서 비행기 카테고리 중 인기 있는 순으로 8개 씩 조회 되게 하기
    public List<Trans> listPopularAirEight() {
        return service.listPopularAirEight();
    }

    @GetMapping("id/{id}")
    public Trans get(@PathVariable Integer id) {
        return service.get(id);
    }

    @PutMapping("edit")
    public void edit ( Trans trans,
                       @RequestParam(value = "removeMainImageId",required = false) Integer removeMainImageId,
                       @RequestParam(value = "transMainImage", required = false) MultipartFile transMainImage,
                       @RequestParam(value = "removeContentImageIds[]", required = false) List<Integer> removeContentImageIds,
                       @RequestParam(value = "transContentImages[]", required = false)MultipartFile[] transContentImages) throws IOException {
        service.update(trans, removeMainImageId, transMainImage, removeContentImageIds, transContentImages);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping("bucket/id/{id}")
    public List<TransBucket> getTransBucket(@PathVariable String id) {

        return transBucketService.getByUserId(id);
    }

    @GetMapping("pay/{id}")
    public Map<String, Object> getTransPayById(@PathVariable Integer id,
                                               @SessionAttribute(value = "login", required = false)Member login) {

        Trans trans = transPayService.getTransPayById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("name", login.getUserName());
        map.put("email", login.getUserEmail());
        map.put("phoneNumber", login.getUserPhoneNumber());
        return Map.of("trans", trans, "member", map);
    }
}
