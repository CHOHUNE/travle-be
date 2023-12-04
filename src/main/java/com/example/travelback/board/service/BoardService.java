package com.example.travelback.board.service;

import com.example.travelback.board.domain.Board;
import com.example.travelback.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    public Map<String ,Object> list(Integer page) {

            Map<String,Object> map= new HashMap<>();
            Map<String,Object> pageInfo= new HashMap<>();

            //전체페이지 확인
            int countAll= mapper.countAll();
            int lastPageNumber= (countAll-1)/5+1;
            int startPageNumber= (page-1)/5*5+1;
            int endPageNumber = startPageNumber+4;
            endPageNumber= Math.min(endPageNumber,lastPageNumber);

            pageInfo.put("startPageNumber",startPageNumber);
            pageInfo.put("endPageNumber",endPageNumber);

        // 페이지
        int from=(page-1)*5;
        map.put("boardList",mapper.selectAll(from));
        map.put("pageInfo",pageInfo);
        return  map;
    }

    public Board id(Integer id) {
        return mapper.id(id);
    }

    public boolean remove(Integer id) {
      return mapper.remove(id)==1;
    }

    public boolean update(Board board) {
        mapper.update(board);
        return false;
    }
}
