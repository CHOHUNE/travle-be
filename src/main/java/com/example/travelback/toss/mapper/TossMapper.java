package com.example.travelback.toss.mapper;

import com.example.travelback.toss.domain.HotelToss;
import com.example.travelback.toss.domain.Toss;
import com.example.travelback.toss.domain.TransToss;
import com.example.travelback.user.dto.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface TossMapper {

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
                requested,
                paymentKey
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
                #{transToss.requested},
                #{transToss.paymentKey}
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
            SELECT *
            FROM hoteltosspay
            """)
    List<HotelToss> getHotelTossAll(String userId);

    // 회원이 결제를 조회 했다면 해당 회원 id기준으로 내역 조회
    // 회원 운송 결제 조회
    @Select("""
        SELECT * FROM transtosspay
        where userId=#{userId};
        """)
    List<TransToss> getTransTossByUserId(String userId);
    // 회원 호텔 결제 조회
    @Select("""
        SELECT * FROM hoteltosspay
        where userId=#{userId};
        """)
    List<HotelToss> getHotelTossByUserId(String userId);


    @Update("""
            UPDATE transtosspay
            SET 
                reservNumber = #{reservNumber}
            WHERE tossId = #{tossId}
            """)
    void saveByTossIdAndUserId(String tossId, String reservNumber);

    // 호텔 상품 결제 저장
    @Insert("""
            INSERT INTO hoteltosspay (
                orderId, 
                userId, 
                userName, 
                guestName, 
                cellPhoneNumber, 
                personAdult, 
                personChild, 
                hotelId, 
                hotelName, 
                checkinDate, 
                checkoutDate, 
                amount, 
                plusMessage, 
                roomtype,
                paymentKey        
                ) 
            VALUES (
                #{hotelToss.orderId},
                #{userId},
                #{hotelToss.userName},
                #{hotelToss.guestName},
                #{hotelToss.cellPhoneNumber},
                #{hotelToss.personAdult},
                #{hotelToss.personChild},
                #{hotelToss.hotelId},
                #{hotelToss.hotelName},
                #{hotelToss.checkinDate},
                #{hotelToss.checkoutDate},
                #{hotelToss.amount},
                #{hotelToss.plusMessage},
                #{hotelToss.roomtype},
                #{hotelToss.paymentKey}
                );
        """)
    int hotelSave(HotelToss hotelToss, String userId);



}

