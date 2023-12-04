package com.example.travelback.comment.mapper;

import com.example.travelback.comment.domain.Comment;
import com.example.travelback.user.dto.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

    @Insert("""
                insert into travel.comment (boardId, userId, comment) values (#{boardId}, #{userId}, #{comment});
        """)

    int add(Comment comment);
}
