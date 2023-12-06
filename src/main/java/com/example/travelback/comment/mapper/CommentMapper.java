

package com.example.travelback.comment.mapper;

import com.example.travelback.comment.domain.Comment;
import com.example.travelback.user.dto.Member;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("""
                insert into travel.comment (boardId, userId, comment) values (#{boardId}, #{userId}, #{comment});
        """)

    int add(Comment comment);


    @Select("""
                    select * from travel.comment where boardId=#{boardId};
    """)
    List<Comment> selectByBoardId(Integer boardId);

    @Delete("""
                delete from travel.comment where id = #{id};
        """)

    int remove(Integer id);
}



