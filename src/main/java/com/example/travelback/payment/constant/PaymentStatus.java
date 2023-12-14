package com.example.travelback.payment.constant;

public enum PaymentStatus {
    CARD("카드"), CASH("현금");

    private String description;

    PaymentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
