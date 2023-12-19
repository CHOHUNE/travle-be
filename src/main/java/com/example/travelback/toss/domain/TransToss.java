package com.example.travelback.toss.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
// 운송 상품 결제 domain
public class TransToss {
    // 결제 번호
    private Integer tossId;
    // 토스에서 발급해주는 주문 번호
    private String orderId;
    // 운송 상품을 예약한 고객 아이디
    private String userId;
    // 운송 상품을 예약한 고객 이름
    private String userName;
    // 운송 상품 실 이용자 이름
    private String realUserName;
    // 운송 상품 이용자 휴대폰 번호
    private String realUserPhoneNumber;
    // 예약한 운송 상품의 이용자 수
    private Integer people;
    // 예약한 운송 상품 id
    private Integer transId;
    // 예약한 운송 상품 제목
    private String transTitle;
    // 예약한 운송 상품의 출발일
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime transStartDay;
    // 예약한 운송 상품 결제 가격
    private Integer amount;
    // 예약한 운송 상품의 고객 요청사항
    private String requested;
    // 운송 상품을 결제한 시간
    private LocalDateTime inserted;
    // 관리자로부터 받은 예약번호
    private String reservNumber;
    // 결제 타입이 운송이라고 설정
    private String category;
    // 결제 키
    private String paymentKey;
}
