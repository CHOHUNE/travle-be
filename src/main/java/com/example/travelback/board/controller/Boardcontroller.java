package com.example.travelback.board.controller;


import com.example.travelback.board.domain.Board;
import com.example.travelback.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")

public class Boardcontroller {

    private  final BoardService service;

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


    @GetMapping("list")
    public List<Board> list(){
        return service.list();
    }


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
