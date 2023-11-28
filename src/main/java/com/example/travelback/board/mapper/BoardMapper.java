package com.example.travelback.board.mapper;

import com.example.travelback.board.domain.Board;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardMapper {


    @Insert("""
            insert into prj.board (title, content, writer) values (#{title},#{content},#{writer});
        """)
    int add(Board board);

    @Select("""
                 select * from prj.board;
       """)

    List<Board> list();

    @Select("""
                select * from prj.board where id=#{id};
        """)
    Board id(Integer id);

    @Delete("""
                delete from prj.board where id=#{id};
        """)
    int remove(Integer id);
}
