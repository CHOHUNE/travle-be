package com.example.travelback.hotelPage.domain;


import lombok.Data;

import java.time.LocalDate;

@Data
public class Like {
    private Integer id;
    private Integer hotelId;
    private String userId;
    private String name;
    private String mainImgUrl;
    private String location;
    private String roomType;
    private LocalDate createdAt;
}
