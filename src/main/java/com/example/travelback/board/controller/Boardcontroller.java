package com.example.travelback.board.controller;


import com.example.travelback.board.domain.Board;
import com.example.travelback.board.service.BoardService;
import com.example.travelback.user.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")

public class Boardcontroller {

    private  final BoardService service;

    @GetMapping("list")
    public List<Board> list(){
        return service.list();
    }





    @PostMapping("add")
    public ResponseEntity add(@RequestBody Board board,
                              @SessionAttribute(value = "login" ,required = false)Member login){
        System.out.println("Board = " + board);
        System.out.println("login = " + login);

        // 로그인 여부확인
        if(login==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // 검증하기
        if (!service.validate(board)){
            return ResponseEntity.badRequest().build();
        }

        // 글 추가 하기
        if (service.add(board,login)){
        return     ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.internalServerError().build();
        }
    }


    // api/board/list?p=4
//    @GetMapping("list")
//    public Map<String ,Object> list(@RequestParam(value = "p",defaultValue = "1")Integer page){
//        return service.list(page);
//    }


    @GetMapping("id/{id}")
    public Board getId(@PathVariable Integer id){
        return  service.id(id);
    }


    @DeleteMapping("remove/{id}")
    public ResponseEntity remove(@PathVariable Integer id,
                                 @SessionAttribute(value = "login",required = false)Member login){

        //401 권한 없음 에러
        if (login==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        //403 서버에서 거절
        if (!service.hasAccess(id,login)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }


         if (service.remove(id)){
             return ResponseEntity.ok().build();
         }else {
             return ResponseEntity.internalServerError().build();
         }
    }

    @PutMapping("edit")
    public  ResponseEntity update(
            @RequestBody Board board, @SessionAttribute (value = "login",required = false)Member login){

        if (login==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (!service.hasAccess(board.getId(),login)){
            return  ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if (service.update(board)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }

    }

















}
