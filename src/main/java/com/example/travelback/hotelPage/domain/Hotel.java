package com.example.travelback.hotelPage.domain;

// Hotel.java

import lombok.Data;

@Data
public class Hotel {
    private Long id;
    private String name;
    private String location;
    private String mainImage;
    private String description;
    private double rating;
    private int numberOfBed;
    private int hotelRating;
}



