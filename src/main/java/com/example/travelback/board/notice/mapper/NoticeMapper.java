package com.example.travelback.board.notice.mapper;

import com.example.travelback.board.notice.domain.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoticeMapper {


    @Select("""
                select * from travel.notice where type=#{type}; 
        """)
    List<Notice> list(Integer type);
}
