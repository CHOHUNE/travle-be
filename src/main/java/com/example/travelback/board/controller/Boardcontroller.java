package com.example.travelback.board.controller;


import com.example.travelback.board.domain.Board;
import com.example.travelback.board.service.BoardService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity add(@RequestBody Board board){
        System.out.println("Board = " + board);

        // 검증하기
        if (!service.validate(board)){
            return ResponseEntity.badRequest().build();
        }

        // 글 추가 하기
        if (service.add(board)){
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
    public ResponseEntity remove(@PathVariable Integer id){
         if (service.remove(id)){
             return ResponseEntity.ok().build();
         }else {
             return ResponseEntity.internalServerError().build();
         }
    }

    @PutMapping("edit")
    public  void update(
            @RequestBody Board board){
        System.out.println("board = " + board);

        service.update(board);

    }

















}
