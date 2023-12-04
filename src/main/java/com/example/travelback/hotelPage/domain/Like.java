package com.example.travelback.hotelPage.domain;


import lombok.Data;

@Data
public class Like {
    private Integer id;
    private Integer hotelId;
    private String userId;
}
