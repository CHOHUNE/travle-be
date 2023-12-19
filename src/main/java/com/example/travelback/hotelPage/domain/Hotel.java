package com.example.travelback.hotelPage.domain;

// Hotel.java

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class Hotel {
    private Long hid;
    private String name;
    private String location;

    private int numberOfRoom;

    private String description;

    private String lodgingType;
    private String rating;

    private String mainImgUrl;
    private String subImgUrl1;
    private String subImgUrl2;
    private String mapImgUrl;

    private String pet;
    private String pool;
    private String oceanview;
    private String familyMood;
    private String romanticMood;
    private String campingMood;

    private String natureMood;
    private String surfing;
    private String winterSport;



    private String minSalePriceWeekday;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime salesFrom;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime salesTo;

    private String cautionMessage;

}





