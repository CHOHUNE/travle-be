package com.example.travelback.hotelPage.domain;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Reservation{

    private int hrId;
    private int hId;
    private String guestName;
    private Date checkinDate;
    private Date checkoutDate;
    private int numberOfGuests;
    private boolean isConfirmed;
    private Date createdAt;

}
