package com.example.travelback.toss.mapper;

import com.example.travelback.toss.domain.Toss;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TossMapper {

    @Insert("""
            insert into travel.ttoss (
                id, 
                amount,
                orderId,
                requested, 
                phoneNumber,
                userId
                ) 
            values (
                #{id},
                #{amount},
                #{orderId},
                #{requested},
                #{phoneNumber},
                #{userId}
                );
        """)
    int save(Integer id,
             Integer amount,
             String orderId,
             String requested,
             String phoneNumber,
             String userId);


    @Select("""
                select tossid,transTitle,transStartDate,transEndDate,requested,reservation,userId,amount
                 from travel.ttoss 
                  left join travel.transport t on t.tId = ttoss.id 
                  where  userId=#{userId};
        """)
    List<Toss> getId(String userId);


//    // 운송 상품 결제 시 db저장 기능
//    @Insert("""
//         INSERT INTO transtosspay (
//            orderId,
//            userId,
//            userName,
//            realUserName,
//            realUserPhoneNumber,
//            people,
//            transId,
//            transTitle,
//            transStartDay,
//            amount,
//            requested,
//            inserted
//         ) VALUES (
//         )
//    """)
//    int save();
}

