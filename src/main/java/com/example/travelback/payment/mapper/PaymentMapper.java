//package com.example.travelback.payment.mapper;
//
//import com.example.travelback.payment.Payment.Payment;
//import com.example.travelback.payment.dto.PaymentDto;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
//
//@Mapper
//public interface PaymentMapper {
//    @Insert("""
//            INSERT INTO payment (amount, paymentName, paymentUid, paySuccessYN, status)
//            VALUES (#{amount}, #{paymentName}, #{paymentUid}, #{paySuccessYN}, #{status})
//            """)
//    int insertByDTO(PaymentDto paymentDto);
//
//    @Select("""
//            SELECT *
//            FROM payment
//            WHERE paymentUid = #{paymentUid}
//            """)
//    Payment selectByDTO(PaymentDto paymentDto);
//
//    @Update("""
//            UPDATE payment
//            SET
//                memberId = #{memberId}
//            WHERE paymentUid = #{paymentUid}
//            """)
//    void updateMemberIdByPayment(Payment payment);
//
//    @Select("""
//            SELECT *
//            FROM payment
//            WHERE paymentUid = #{orderId}
//            """)
//    Payment findByUid(String orderId);
//}
