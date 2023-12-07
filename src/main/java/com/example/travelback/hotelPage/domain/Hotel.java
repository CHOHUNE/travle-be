package com.example.travelback.hotelPage.domain;

// Hotel.java

import lombok.Data;

@Data
public class Hotel {
    private Long hid;
    private String name;
    private String location;
//    private String mainImg;
    private String description;
    private double rating;
    private int numberOfBed;
    private String review;
    private String roomType;
    private String subImg1;
    private String subImg2;
    private String mapImg;
    private String numberOfBedRooms;
    private Double totalPrice;

    private String mainImgUrl;
    private String subImgUrl1;
    private String subImgUrl2;
    private String mapImgUrl;

}





