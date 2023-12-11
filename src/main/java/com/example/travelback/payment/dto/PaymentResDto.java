package com.example.travelback.payment.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResDto {
    private String payType;
    private Long amount;
    private String orderName;
    private String orderId;
    private String customerEmail;
    private String customerName;
    private String succerssUrl;
    private String failUrl;

    private String failReason;
    private boolean cancelYN;
    private String cancelReason;
    private String createdAt;
}
