package com.example.travelback.hotelPage.domain;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Reservation{
    private Long id;
    private Hotel hotel;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String guestName;
    private int numberOfGuests;
    private boolean isConfirmed;
    private LocalDateTime createdAt;
    private String mapImageUrl;
    private String subImage1;
    private String subImage2;
    private double totalPrice;
}
