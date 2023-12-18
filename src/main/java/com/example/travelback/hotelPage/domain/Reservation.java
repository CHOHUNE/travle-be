package com.example.travelback.hotelPage.domain;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Reservation{

    private int hrId;
    private int hId;
    private int hrtId;
    private String guestName;
    private Date checkinDate;
    private Date checkoutDate;
    private String numberOfGuests;
    private boolean isConfirmed;
    private Date createdAt;
    private int totalPrice;
    private String cellPhoneNumber;
    private String plusMessage;
    private String roomtype;

}
