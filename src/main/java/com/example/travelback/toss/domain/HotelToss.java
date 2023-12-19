package com.example.travelback.toss.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
// 호텔 상품 결제 domain
public class HotelToss {
    // 호텔 결제 번호
    private Integer hotelTossId;
    // 토스에서 발급해주는 주문 번호
    private String orderId;
    // 운송 상품을 예약한 고객 아이디
    private String userId;
    // 운송 상품을 예약한 고객 이름
    private String userName;
    // 운송 상품 실 이용자 이름
    private String guestName;
    // 운송 상품 이용자 휴대폰 번호
    private String cellPhoneNumber;
    // 성인 인원 수
    private Integer personAdult;
    // 성인 인원 수
    private Integer personChild;
    // 호텔 상품 id
    private Integer hotelId;
    // 호텔 상품 이름
    private String hotelName;
    // 체크인 날짜
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime checkinDate;
    // 체크 아웃 날짜
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime checkoutDate;
    // 호텔 상품 결제 가격
    private Integer amount;
    // 호텔 상품의 고객 요청사항
    private String plusMessage;
    // 호텔 상품의 룸 타입
    private String roomtype;
    // 운송 상품을 결제한 시간
    private LocalDateTime inserted;
    // 관리자로부터 받은 예약번호
    private String reservNumber;
    // 결제 타입이 호텔 이라고 설정
    private String category;
    // 결제 키
    private String paymentKey;
    // 결제 상태 (예약접수, 예약완료, 취소중, 취소완료)
    private String reservStatus;
}
