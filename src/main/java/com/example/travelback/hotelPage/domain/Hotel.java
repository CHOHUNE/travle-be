package com.example.travelback.hotelPage.domain;

// Hotel.java

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Hotel {
    private Long hid;
    private String name;
    private String location;
    private String rating;
    private int numberOfBed;
    private String review;
    private String roomType;
    private String description;

    private String lodgingType;
    private Double cheapestPrice;

    private String mainImgUrl;
    private String subImgUrl1;
    private String subImgUrl2;
    private String mapImgUrl;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime salesFrom;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime salesTo;

    private String cautionMessage;

}





