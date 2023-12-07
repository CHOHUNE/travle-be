package com.example.travelback.board.mapper;

import com.example.travelback.board.domain.BoardFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {

    @Insert("""
                insert into travel.boardfile (boardId, name) values (#{boardId},#{name});
        """)

    int insert(Integer boardId, String name);

    @Select("""
                select id,name from travel.boardfile where boardId=#{boardId};
        """)

    List<BoardFile> selectNamesByBoardId(Integer boardId);
}
