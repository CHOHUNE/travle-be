package com.example.travelback.board.mapper;

import com.example.travelback.board.domain.Board;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Mapper
public interface BoardMapper {


    @Select("""
                 select * from board;
       """)

    List<Board> list();



    @Insert("""
            insert into board (title, content, writer) values (#{title},#{content},#{writer});
        """)
    int add(Board board);
//
//    @Select("""
//                 select * from board LIMIT #{from},5;
//       """)
//
//    List<Board> selectAll(Integer from);

    @Select("""
                select * from board where id=#{id};
        """)
    Board id(Integer id);

    @Delete("""
                delete from board where id=#{id};
        """)
    int remove(Integer id);

    @Update("""
                    update board set content = #{content}, title=#{title}, writer=#{writer} where id=#{id};
        """)

    int update(Board board);
//
//    @Select("""
//                select count(*) from board;
//        """)
//    int countAll();
}
