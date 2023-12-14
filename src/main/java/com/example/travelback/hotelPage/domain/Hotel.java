package com.example.travelback.hotelPage.domain;

// Hotel.java

import lombok.Data;

@Data
public class Hotel {
    private Long hid;
    private String name;
    private String location;
    private double rating;
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
}





