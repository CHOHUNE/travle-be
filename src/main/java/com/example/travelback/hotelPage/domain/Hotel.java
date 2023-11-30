package com.example.travelback.hotelPage.domain;

// Hotel.java

import lombok.Data;

@Data
public class Hotel {
    private Long hId;
    private String name;
    private String location;
    private String mainImg;
    private String description;
    private double rating;
    private int numberOfBed;
    private String review;
    private Double totalPrice;
}





