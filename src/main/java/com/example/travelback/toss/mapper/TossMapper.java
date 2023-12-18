package com.example.travelback.toss.mapper;

import com.example.travelback.toss.domain.Toss;
import com.example.travelback.toss.domain.TransToss;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
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
                select tossid,transTitle,transStartDate,transEndDate,requested,reservation,userId,amount, phoneNumber
                 from travel.ttoss 
                  left join travel.transport t on t.tId = ttoss.id 
                  where  userId=#{userId};
        """)
    List<Toss> getId(String userId);

    @Select("""
            SELECT *
            FROM ttoss
            LEFT JOIN travel.transport t ON t.tId = ttoss.id
            """)
    List<Toss> getAll(String userId);

    // 운송 상품 저장 
    @Insert("""
            INSERT INTO transtosspay (
                orderId,
                userId,
                userName,
                realUserName,
                realUserPhoneNumber,
                people,
                transId,
                transTitle,
                transStartDay,
                amount,
                requested
                ) 
            VALUES (
                #{transToss.orderId},
                #{userId},
                #{transToss.userName},
                #{transToss.realUserName},
                #{transToss.realUserPhoneNumber},
                #{transToss.people},
                #{transToss.transId},
                #{transToss.transTitle},
                #{transToss.transStartDay},
                #{transToss.amount},
                #{transToss.requested}
                );
        """)
    int transSave(TransToss transToss,
                  String userId);


    // 조회한 사람이 admin일 경우 모든 운송 결제 내역 조회
    @Select("""
            SELECT *
            FROM transtosspay
            """)
    List<TransToss> getTransTossAll(String userId);

    @Select("""
        SELECT * FROM transtosspay
        where userId=#{userId};
        """)
    List<TransToss> getTransTossByUserId(String userId);
}

