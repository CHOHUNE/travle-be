package com.example.travelback.trans.controller;

import com.example.travelback.trans.dto.TransLike;
import com.example.travelback.trans.service.TransLikeService;
import com.example.travelback.user.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transLike")
public class TransLikeController {
    private final TransLikeService service;

    @PostMapping
    public ResponseEntity<Map<String, Object>> like(@RequestBody TransLike transLike,
                                    @SessionAttribute(value = "login",required = false) Member login) {
        if (login == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // 찜 버튼이 성공적으로 입력 되면 프론트로 해당 상품의 찜 갯수와 로그인한 유저가 찜한지 여부를 리턴한다.
        return ResponseEntity.ok(service.update(transLike, login));
    }
}
