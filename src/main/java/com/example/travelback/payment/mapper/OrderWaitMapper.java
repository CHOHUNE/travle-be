//package com.example.travelback.payment.mapper;
//
//import com.example.travelback.payment.order.Orders;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Mapper;
//
//@Mapper
//public interface OrderWaitMapper {
//    @Insert("""
//            INSERT INTO orderWait (
//                orderId,
//                orderName,
//                productName,
//                quantity,
//                amount,
//                ordererName,
//                ordererPhone,
//                ordererEmail,
//                ordererAddress,
//                deliveryName,
//                deliveryPhone,
//                deliveryAddress,
//                deliveryComment)
//            VALUES (
//                #{orderId},
//                #{orderName},
//                #{productName},
//                #{quantity},
//                #{amount},
//                #{ordererName},
//                #{ordererPhone},
//                #{ordererEmail},
//                #{ordererAddress},
//                #{deliveryName},
//                #{deliveryPhone},
//                #{deliveryAddress},
//                #{deliveryComment})
//                """)
//    void insert(Orders orders);
//}
