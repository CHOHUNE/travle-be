/*
package com.example.travelback.board.boardLike.mapper;

import com.example.travelback.board.boardLike.domain.BoardLike;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardLikeMapper {

    CREATE TABLE boardLike (
            id INT PRIMARY KEY AUTO_INCREMENT,
            memberId VARCHAR(100) REFERENCES member(userId),
    boardId INT  REFERENCES board(id)
);




    @Insert("""
            insert into travel.boardlike (memberId, boardId) values (#{boardId},#{memberId});
        """)
    int like(BoardLike boardLike);

    @Delete("""
                delete from travel.boardlike where boardId=#{boardId} and memberId=#{memberId};
        """)
    int delete(BoardLike boardLike);
}

*/