package com.example.travelback.toss.mapper;

import com.example.travelback.toss.domain.Toss;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

@Mapper
public interface TossMapper {

    @Insert("""
                insert into travel.ttoss (id, amount ) values (#{id},#{amount});
        """)

    int save(Toss toss);



}
