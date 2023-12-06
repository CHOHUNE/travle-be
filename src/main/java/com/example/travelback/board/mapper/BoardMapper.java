package com.example.travelback.board.mapper;

import com.example.travelback.board.domain.Board;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {


    @Select("""
                 select * from board
                 where  content like #{keyword} 
                       or title like #{keyword}
                       or writer like  #{keyword}
                       
                       
                  LIMIT #{from},5
       """)

    List<Board> selectAll(int from, String keyword);


//
//    @Select("""
//                 select * from board LIMIT #{from},5;
//       """)
//
//    List<Board> selectAll(Integer from);

    //
//    @Select("""
//                select count(*) from board;
//        """)
//    int countAll();


    @Insert("""
            insert into board (title, content, writer) values (#{title},#{content},#{writer});
        """)
    int add(Board board);

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
    @Select("""
        SELECT id, title, content, writer, inserted
        FROM board
        WHERE id = #{id}
        """)
    Board selectById(Integer id);

    @Select("""
                select count(*) from board 
                 where  content like #{keyword} 
                       or title like #{keyword}
                       or writer like  #{keyword}
        """)
    int countAll(String keyword);


}
