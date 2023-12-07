
package com.example.travelback.comment.controller;

import com.example.travelback.comment.service.CommentService;
import com.example.travelback.comment.domain.Comment;
import com.example.travelback.user.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment/")
public class CommentController {

    private final CommentService service;

    @PostMapping("add")
    public ResponseEntity add(@RequestBody Comment comment,
                              @SessionAttribute (value = "login" ,required = false )Member login){
        System.out.println("comment = " + comment);

        if (login==null){
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (service.add(comment,login)){
            return  ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("list")
    public List<Comment> list(@RequestParam("id")Integer boardId){
        return service.list(boardId);
    }


    @DeleteMapping("{id}")
    public void remove(@PathVariable Integer id){
        service.revmove(id);
        System.out.println("id 삭제 되었습니다. = " + id);

    }
}
