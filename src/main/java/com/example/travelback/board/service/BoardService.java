package com.example.travelback.board.service;

import com.example.travelback.board.domain.Board;
import com.example.travelback.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper mapper;

    public boolean add(Board board) {
     return  mapper.add(board)==1;
    }

    public boolean validate( Board board){
        if (board==null){
            return  false;
        }
        if (board.getContent()==null||board.getContent().isBlank()){
            return false;
        }
        if (board.getWriter()==null ||board.getWriter().isBlank()){
            return false;
        }
        if (board.getTitle()==null||board.getTitle().isBlank()){
            return false;
        }
        return true;


    }


    public List<Board> list() {
        return mapper.list();
    }

    public Board id(Integer id) {
        return mapper.id(id);
    }

    public boolean remove(Integer id) {
      return mapper.remove(id)==1;
    }

    public void update(Board board) {
    }
}
